package com.codewithproject.springsecurity.repository;

import com.codewithproject.springsecurity.entities.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LineRepository extends JpaRepository<Line, Long> {

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE tb_bb_line", nativeQuery = true)
    void truncateTable();

    @Query(value = "SELECT l.* "
            + " FROM tb_bb_line l ", nativeQuery = true)
    List<Line> getListLine();

    @Query(value = "SELECT DISTINCT "
                     + " l.blade_unit_id "
                     + " , l.id "
                     + " , l.period_cnt "
                     + " , l.period "
                     + " , l.init_price "
                     + " , l.deposit "
                     + " , l.depreciation "
                     + " , l.fee "
                     + " , l.end_price "
                 + " FROM tb_bb_line l, tb_bb_line_progress lp "
                 + " WHERE lp.line_id = l.id ", nativeQuery = true)
    List<Line> getLineHaveProgress();
}
