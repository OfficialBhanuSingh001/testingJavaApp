package com.predictorInfotech.sarkariUpdates.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menu_details")
public class MenuDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_item", nullable = false)
    private MenuItem menuItem;

    @Column(name = "posted_date", nullable = false)
    private LocalDateTime postedDate;

    @Column(name = "meta_title")
    private String metaTitle;

    @Column(name = "meta_description", columnDefinition = "TEXT")
    private String metaDescription;

    @Column(name = "slug", unique = true, nullable = false)
    private String slug;

    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "description", nullable = false, columnDefinition = "MEDIUMTEXT")
    private String description;

    @Column(name = "apply_start_date")
    private LocalDateTime applyStartDate;

    @Column(name = "apply_last_date")
    private LocalDateTime applyLastDate;

    @Column(name = "notification_date")
    private LocalDateTime notificationDate;

    @Column(name = "admit_card_date")
    private LocalDateTime admitCardDate;

    @Column(name = "result_date")
    private LocalDateTime resultDate;

    @Lob
    @Column(name = "applicable_fee", columnDefinition = "TEXT")
    private String applicableFee;

    @Lob
    @Column(name = "age_limit", columnDefinition = "TEXT")
    private String ageLimit;

    @Column(name = "total_post")
    private Integer totalPost;

    @Lob
    @Column(name = "vacancy_details", columnDefinition = "TEXT")
    private String vacancyDetails;

    @Lob
    @Column (name = "faq_questions", columnDefinition = "MEDIUMTEXT")
    private String faqQuestions;

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
