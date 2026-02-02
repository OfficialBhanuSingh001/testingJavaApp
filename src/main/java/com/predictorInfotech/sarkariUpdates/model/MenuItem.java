    package com.predictorInfotech.sarkariUpdates.model;

    import jakarta.persistence.*;
    import lombok.*;
    import org.hibernate.annotations.AnyDiscriminatorImplicitValues;

    import java.time.LocalDateTime;

    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Data
    @Table(name = "menu_items")
    public class MenuItem {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "menu_name", length = 80, unique = true)
        private String menuName;

        @Column(name = "menu_slug", unique = true, nullable = false)
        private String menuSlug;

        @Column(name = "menu_indexing", unique = true, nullable = false)
        private Integer menuIndexing;

        @Column(name = "is_active")
        private Boolean isActive = true;

        @Column(name = "is_delete")
        private Boolean isDelete = false;

        // ===== Audit =====
        @Column(name = "created_at", nullable = false, updatable = false)
        private LocalDateTime createdAt;

        @Column(name = "created_by")
        private Long createdBy;

        @Column(name = "updated_at")
        private LocalDateTime updatedAt;

        @Column(name = "updated_by")
        private Long updatedBy;

        @Column(name = "deleted_at")
        private LocalDateTime deletedAt;

        @Column(name = "deleted_by")
        private Long deletedBy;

        // ===== Transaction Fields =====

        @Column(name = "workstation_id")
        private Long workstationId;

        // ===== Hooks =====
        @PrePersist
        protected void onCreate() {
            this.createdAt = LocalDateTime.now();
        }

        @PreUpdate
        protected void onUpdate() {
            this.updatedAt = LocalDateTime.now();
        }
    }
