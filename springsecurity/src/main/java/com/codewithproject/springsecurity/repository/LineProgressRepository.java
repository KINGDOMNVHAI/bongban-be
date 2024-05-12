package com.codewithproject.springsecurity.repository;

import com.codewithproject.springsecurity.entities.Line;
import com.codewithproject.springsecurity.entities.LineProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LineProgressRepository extends JpaRepository<LineProgress, Long> {

    @Query(value = "SELECT l.* "
            + " FROM tb_bb_line_progress l ", nativeQuery = true)
    List<Line> getListLineProgress();
}
