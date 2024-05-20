package com.codewithproject.springsecurity.repository;

import com.codewithproject.springsecurity.entities.Line;
import com.codewithproject.springsecurity.entities.LineProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LineProgressRepository extends JpaRepository<LineProgress, Long> {

    @Query(value = "SELECT lp.* "
            + " FROM tb_bb_line_progress lp ", nativeQuery = true)
    List<Line> getListLineProgress();

    @Query(value = "SELECT count(lp.line_id) "
            + " FROM tb_bb_line_progress lp "
            + " LEFT JOIN tb_bb_line l ON l.id LIKE lp.line_id "
            + " LEFT JOIN tb_bb_blade_unit bu ON bu.unit_id LIKE l.blade_unit_id "
            + " WHERE bu.blade_cd LIKE :bladeCD ", nativeQuery = true)
    Integer countByBladeCD(@Param("bladeCD") String bladeCD);
}
