package com.dhatvibs.repository;

import com.dhatvibs.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {

    List<Area> findByCityIdAndIsActiveTrueOrderByNameAsc(Long cityId);
}
