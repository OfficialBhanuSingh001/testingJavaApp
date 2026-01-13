package com.wayinfotechsolutions.wayinfotechsolutionswebsite.projection;

public interface SolutionProjection {

    Long getSolutionId();

    String getSolutionName();

    String getSolutionSlug();


    Integer getSolutionOrder();

    Long getNavId();
    String getNavName();
    String getNavSlug();
    Integer getNavOrder();
    Long getSubNavId();
    String getSubNavName();
    String getSubNavSlug();
    Integer getSubNavOrder();
    String getHeading();
    String getTitle();
    String getDescription();
}
