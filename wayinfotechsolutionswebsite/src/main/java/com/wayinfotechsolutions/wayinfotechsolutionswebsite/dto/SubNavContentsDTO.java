package com.wayinfotechsolutions.wayinfotechsolutionswebsite.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubNavContentsDTO {

    @NotNull(message = "Sub Nav is required")
    private Long subNavId;

    @NotBlank(message = "Heading is required")
    @Size(max = 255, message = "Heading must not exceed 255 characters")
    private String heading;

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    // ===== Audit =====

    private LocalDateTime createdAt;

    private Long createdBy;

    private LocalDateTime updatedAt;

    private Long updatedBy;

    private LocalDateTime deletedAt;

    private Long deletedBy;
}
