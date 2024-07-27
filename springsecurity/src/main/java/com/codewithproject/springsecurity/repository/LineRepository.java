package com.codewithproject.springsecurity.repository;

import com.codewithproject.springsecurity.entities.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
}
