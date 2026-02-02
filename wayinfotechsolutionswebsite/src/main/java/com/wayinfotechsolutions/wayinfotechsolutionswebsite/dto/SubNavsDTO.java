package com.wayinfotechsolutions.wayinfotechsolutionswebsite.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SubNavsDTO {

    private Long subNavId;

    @NotNull(message = "Nav ID is required")
    @Positive(message = "Nav ID must be a positive number")
    private Long navId;

    @NotBlank(message = "Nav slug must not be blank")
    @Size(min = 2, max = 100, message = "Nav slug must be between 2 and 100 characters")
    private String subNavSlug;

    @NotBlank(message = "Nav name must not be blank")
    @Size(min = 2, max = 100, message = "Nav name must be between 2 and 100 characters")
    private String subNavName;

    @NotNull(message = "Nav order is required")
    @Positive(message = "Nav order must be a positive number")
    private Integer subNavOrder;

    // ===== Audit =====

    private LocalDateTime createdAt;

    private Long createdBy;

    private LocalDateTime updatedAt;

    private Long updatedBy;

    private LocalDateTime deletedAt;

    private Long deletedBy;
}
