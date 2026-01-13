package com.wayinfotechsolutions.wayinfotechsolutionswebsite.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

@Table(name="solutions")

public class Solutions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="solution_id")
    private Long solutionId;

    @Column(name = "solution_slug", length = 100, nullable = false, unique = true)
    private String solutionSlug;

    @Column(name = "solution_name", nullable = false, unique = true)
    private String solutionName;

    @Column(name="solution_order",unique = true)
    @Positive
    private int solutionOrder;

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
