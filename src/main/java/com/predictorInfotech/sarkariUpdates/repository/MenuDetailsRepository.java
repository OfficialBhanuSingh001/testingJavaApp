package com.predictorInfotech.sarkariUpdates.repository;

import com.predictorInfotech.sarkariUpdates.model.MenuDetail;
import com.predictorInfotech.sarkariUpdates.projection.MenuDetailProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuDetailsRepository extends JpaRepository<MenuDetail,Long> {
    List<MenuDetailProjection> findAllProjectedBy();

    Optional<MenuDetail> findBySlug(String slug);
}
