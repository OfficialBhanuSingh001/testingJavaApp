package com.wayinfotechsolutions.wayinfotechsolutionswebsite.projection;

public interface SubNavProjection {
    Long getSubNavId();

    String getSubNavSlug();

    String getSubNavName();

    Integer getSubNavOrder();

    // Nested projection (only ID from Solutions)
    Long getNavId();
}
