package com.sarkariUpdate.OliveMedia.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

    @Entity
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Table(name = "menu_items")

    public class Navs {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        public Long id;
       @Column(name = "menu_slug", length = 100, nullable = false)
        private String menuSlug;

        @Column(name = "menu_name", nullable = false)
        private String menuName;

        @Column(name="menu_indexing")
        @Positive
        private int menuIndexing;

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

        // ===== Automatic Date Handling =====
        @PrePersist
        public void onCreate() {
            createdAt = LocalDateTime.now();
        }

        @PreUpdate
        public void onUpdate() {
            updatedAt = LocalDateTime.now();
        }
}
