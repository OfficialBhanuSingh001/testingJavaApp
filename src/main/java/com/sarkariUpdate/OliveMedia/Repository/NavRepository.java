package com.sarkariUpdate.OliveMedia.Repository;

import com.sarkariUpdate.OliveMedia.Entity.Navs;
import com.sarkariUpdate.OliveMedia.Projections.NavsProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NavRepository extends JpaRepository<Navs, Long> {
    List<NavsProjection> findAllProjectedBy();
}
