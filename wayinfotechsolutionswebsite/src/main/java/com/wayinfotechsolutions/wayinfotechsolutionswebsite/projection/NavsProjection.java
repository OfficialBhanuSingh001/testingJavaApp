package com.wayinfotechsolutions.wayinfotechsolutionswebsite.projection;

public interface NavsProjection {
    Long getNavId();

    String getNavSlug();

    String getNavName();

    Integer getNavOrder();

    // Nested projection (only ID from Solutions)
    Long getSolutionId();
}
