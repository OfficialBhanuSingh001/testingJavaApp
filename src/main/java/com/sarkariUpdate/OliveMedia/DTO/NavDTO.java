package com.sarkariUpdate.OliveMedia.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class NavDTO {

    private Long Id;

    @NotBlank(message = "Menu slug must not be blank")
    @Size(min = 2, max = 100, message = "Menu slug must be between 2 and 100 characters")
    private String menuSlug;

    @NotBlank(message = "Menu name must not be blank")
    @Size(min = 2, max = 100, message = "Menu name must be between 2 and 100 characters")
    private String menuName;

    @NotNull(message = "Menu order is required")
    @Positive(message = "Menu order must be a positive number")
    private Integer menuIndexing;

    // ===== Audit =====

    private LocalDateTime createdAt;

    private Long createdBy;

    private LocalDateTime updatedAt;

    private Long updatedBy;

    private LocalDateTime deletedAt;

    private Long deletedBy;
}
