package com.predictorInfotech.sarkariUpdates.repository;

import com.predictorInfotech.sarkariUpdates.model.MenuItem;
import com.predictorInfotech.sarkariUpdates.projection.MenuItemProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItemProjection> findAllProjectedBy();
}
