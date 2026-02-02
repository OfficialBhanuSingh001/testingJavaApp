package com.wayinfotechsolutions.wayinfotechsolutionswebsite.projection;

public interface SubNavContentsProjection {
    Long getContentId();

    String getHeading();

    String getTitle();

    String getDescription();

    // Nested projection from SubNavs
    SubNavProjection getSubNav();

    interface SubNavProjection {
        Long getSubNavId();
        String getSubNavName();
        String getSubNavSlug();
    }
}
