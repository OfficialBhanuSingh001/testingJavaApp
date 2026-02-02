package com.predictorInfotech.sarkariUpdates.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDetailDTO {

    private Long id;

    // ===== Relation =====
    @NotNull(message = "Menu item is required")
    private Long menuItemId;   // only ID, NOT entity

    // ===== SEO =====
    @FutureOrPresent(message = "Posted date must be today or future")
    @NotNull(message = "Posted date is required")
    private LocalDateTime postedDate;

    @Size(max = 255, message = "Meta title must be under 255 characters")
    private String metaTitle;

    private String metaDescription;

    @NotBlank(message = "Slug is required")
    @Pattern(
            regexp = "^[a-z0-9]+(-[a-z0-9]+)*$",
            message = "Slug must be lowercase, no spaces, hyphen (-) allowed"
    )
    private String slug;

    // ===== Content =====
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    // ===== Dates =====
    @FutureOrPresent(message = "Apply start date must be today or future")
    private LocalDateTime applyStartDate;

    @FutureOrPresent(message = "Apply last date must be today or future")
    private LocalDateTime applyLastDate;

    @FutureOrPresent(message = "Notification date must be today or future")
    private LocalDateTime notificationDate;

    @FutureOrPresent(message = "Admit card date must be today or future")
    private LocalDateTime admitCardDate;

    @FutureOrPresent(message = "Result date must be today or future")
    private LocalDateTime resultDate;

    // ===== Extra Info =====
    private String applicableFee;
    private String ageLimit;

    @Min(value = 0, message = "Total post cannot be negative")
    private Integer totalPost;

    private String vacancyDetails;
    private String faqQuestions;

    // ===== Status =====
    private Boolean isActive = true;
}
