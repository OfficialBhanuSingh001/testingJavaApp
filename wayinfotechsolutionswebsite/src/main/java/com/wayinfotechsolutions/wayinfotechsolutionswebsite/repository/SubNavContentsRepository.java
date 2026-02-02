package com.wayinfotechsolutions.wayinfotechsolutionswebsite.repository;

import com.wayinfotechsolutions.wayinfotechsolutionswebsite.model.SubNavContents;
import com.wayinfotechsolutions.wayinfotechsolutionswebsite.model.SubNavs;
import com.wayinfotechsolutions.wayinfotechsolutionswebsite.projection.SubNavContentsProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubNavContentsRepository extends JpaRepository<SubNavContents,Long> {
    List<SubNavContentsProjection> findAllProjectedBy();

    boolean existsBySubNavId(SubNavs subNav);

    boolean existsByHeadingAndSubNavId(String heading, SubNavs subNav);

    boolean existsByTitleAndSubNavId(String title, SubNavs subNav);
}
