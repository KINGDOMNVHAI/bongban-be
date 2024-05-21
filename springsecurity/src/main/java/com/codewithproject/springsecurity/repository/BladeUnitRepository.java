package com.codewithproject.springsecurity.repository;

import com.codewithproject.springsecurity.entities.BladeUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BladeUnitRepository extends JpaRepository<BladeUnit, Long> {

    @Query(value = "SELECT count(bu.unit_id) "
            + " FROM tb_bb_blade_unit bu "
            + " WHERE bu.blade_cd LIKE :bladeCD ", nativeQuery = true)
    Integer countByBladeCD(@Param("bladeCD") String bladeCD);
}
