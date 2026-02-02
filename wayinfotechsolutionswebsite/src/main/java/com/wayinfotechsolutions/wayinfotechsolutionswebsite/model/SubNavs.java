package com.wayinfotechsolutions.wayinfotechsolutionswebsite.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "sub_nav")

public class SubNavs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_nav_id")
    public Long subNavId;
//   @ManyToOne(mappedBy = "Navs", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nav_id", nullable = false)
    private Navs navId;

    @Column(name = "sub_nav_slug", length = 100, nullable = false)
    private String subNavSlug;

    @Column(name = "sub_nav_name", nullable = false)
    private String subNavName;

    @Column(name="sub_nav_order")
    @Positive
    private int subNavOrder;

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
