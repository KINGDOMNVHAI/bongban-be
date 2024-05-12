package com.codewithproject.springsecurity.repository;

import com.codewithproject.springsecurity.entities.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineRepository extends JpaRepository<Line, Long> {

    @Query(value = "SELECT l.* "
            + " FROM tb_bb_line l ", nativeQuery = true)
    List<Line> getListLine();
}
