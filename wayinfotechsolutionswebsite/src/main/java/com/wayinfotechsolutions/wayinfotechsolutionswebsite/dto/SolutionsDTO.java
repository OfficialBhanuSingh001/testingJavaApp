package com.wayinfotechsolutions.wayinfotechsolutionswebsite.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class SolutionsDTO {
    private  Long solutionId;
    @NotBlank(message = "Name is required")
    private String solutionName;

    @Size(min = 2, max = 50, message = "Solution Slug should be between 2 and 20 characters")
    private String solutionSlug;

    private int solutionOrder;
    // ===== Audit =====

    private LocalDateTime createdAt;

    private Long createdBy;

    private LocalDateTime updatedAt;

    private Long updatedBy;

    private LocalDateTime deletedAt;

    private Long deletedBy;
}
