package com.wayinfotechsolutions.wayinfotechsolutionswebsite.repository;

import com.wayinfotechsolutions.wayinfotechsolutionswebsite.model.Navs;
import com.wayinfotechsolutions.wayinfotechsolutionswebsite.model.SubNavs;
import com.wayinfotechsolutions.wayinfotechsolutionswebsite.projection.NavsProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubNavRepository extends JpaRepository<SubNavs, Long> {
    List<NavsProjection> findAllProjectedBy();
    boolean existsBySubNavNameAndNavIdAndDeletedAtIsNull(String subNavName, Navs nav);

    boolean existsBySubNavSlugAndNavIdAndDeletedAtIsNull(String subNavSlug, Navs nav);

    boolean existsBySubNavOrderAndNavIdAndDeletedAtIsNull(Integer subNavOrder, Navs nav);
}
