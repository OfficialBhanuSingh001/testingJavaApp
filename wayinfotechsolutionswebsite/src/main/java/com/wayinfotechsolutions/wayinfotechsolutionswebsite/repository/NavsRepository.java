package com.wayinfotechsolutions.wayinfotechsolutionswebsite.repository;

import com.wayinfotechsolutions.wayinfotechsolutionswebsite.model.Navs;
import com.wayinfotechsolutions.wayinfotechsolutionswebsite.model.Solutions;
import com.wayinfotechsolutions.wayinfotechsolutionswebsite.projection.NavsProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NavsRepository extends JpaRepository<Navs,Long> {
//    List<NavsProjection> findAllByDeletedAtIsNullOrderByNavOrderAsc();
    List<NavsProjection> findAllProjectedBy();

    boolean existsByNavNameAndSolutionIdAndDeletedAtIsNull(String navName, Solutions solution);

    boolean existsByNavSlugAndSolutionIdAndDeletedAtIsNull(String navSlug, Solutions solution);

    boolean existsByNavOrderAndSolutionIdAndDeletedAtIsNull(Integer navOrder, Solutions solution);
}
