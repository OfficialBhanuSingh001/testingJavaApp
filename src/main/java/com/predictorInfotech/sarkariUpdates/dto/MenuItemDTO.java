package com.predictorInfotech.sarkariUpdates.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemDTO {

    private Long id;

    @NotBlank(message = "Menu name is required")
    @Size(min = 3, max = 80, message = "Menu name must not exceed 80 characters")
    private String menuName;

    @NotBlank(message = "Menu slug is required")
    @Pattern(
            regexp = "^[a-z0-9]+(-[a-z0-9]+)*$",
            message = "Slug must be lowercase, no spaces, and hyphen (-) allowed only"
    )
    private String menuSlug;

    @NotNull(message = "Menu indexing is required")
    @Min(value = 1, message = "Menu indexing must be greater than 0")
    private Integer menuIndexing;

    private Boolean isActive = true;
}
