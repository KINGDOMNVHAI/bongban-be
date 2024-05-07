package com.codewithproject.springsecurity.repository;

import com.codewithproject.springsecurity.entities.Blade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BladeRepository extends JpaRepository<Blade, Long> {

    @Query(value = "SELECT b.* "
            + " FROM tb_bb_blade b ", nativeQuery = true)
    List<Blade> getListBlade();
}
