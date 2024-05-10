package com.codewithproject.springsecurity.repository;

import com.codewithproject.springsecurity.entities.Blade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BladeRepository extends JpaRepository<Blade, Long> {

    @Query(value = "SELECT b.* "
            + " FROM tb_bb_blade b ", nativeQuery = true)
    List<Blade> getListBlade();

    @Query(value = "SELECT b.seq" +
            ", b.brand_cd" +
            ", b.brand_name" +
            ", b.blade_name" +
            ", b.blade_cd" +
            ", b.blade_full_name" +
            ", b.sub_branch" +
            ", b.paddle_tp" +
            ", bu.unit_id" +
            ", bu.inspect_percent" +
            " FROM tb_bb_blade b, tb_bb_blade_unit bu " +
            " WHERE b.blade_cd LIKE bu.blade_cd " +
            " AND b.blade_cd LIKE :bladeCD", nativeQuery = true)
    List<Object[]> getListByBladeCD(@Param("bladeCD") String bladeCD);
}
