package com.wayinfotechsolutions.wayinfotechsolutionswebsite.repository;

import com.wayinfotechsolutions.wayinfotechsolutionswebsite.model.Solutions;
import com.wayinfotechsolutions.wayinfotechsolutionswebsite.projection.SolutionProjection;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

@NamedStoredProcedureQuery(
        name = "GetSolutionFullDataBySlug",
        procedureName = "GetSolutionFullDataBySlug",
        resultSetMappings = "SolutionProjectionMapping",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "solutionSlug", type = String.class)
        }
)

public interface SolutionsRepository extends JpaRepository<Solutions, Long> {


    @Procedure(name = "GetSolutionFullDataBySlug")
    List<SolutionProjection> getSolutionFullDataBySlug(@Param("solutionSlug") String solution_slug);
    List<SolutionProjection> findAllProjectedBy();
}
