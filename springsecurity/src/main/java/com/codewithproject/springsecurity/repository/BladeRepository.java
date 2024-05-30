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

    @Query(value = "SELECT b.seq, b.brand_cd, b.brand_name, b.blade_cd, b.blade_name, b.blade_full_name "
            + ", bu.unit_id, bu.blade_cd "
            + ", l.blade_unit_id, l.period, l.period_cnt"
            + ", l.init_price, l.deposit, l.depreciation, l.fee, l.end_price "
            + "FROM tb_bb_blade b "
            + "JOIN tb_bb_blade_unit bu on bu.blade_cd = b.blade_cd "
            + "JOIN tb_bb_line l on bu.unit_id = l.blade_unit_id ", nativeQuery = true)
    List<Object[]> getListBladeLine();

    @Query(value = "SELECT b.seq, b.brand_cd, b.brand_name, b.blade_cd, b.blade_name, b.blade_full_name "
            + ", bu.unit_id, bu.blade_cd "
            + ", l.blade_unit_id, l.period, l.period_cnt"
            + ", l.init_price, l.deposit, l.depreciation, l.fee, l.end_price "
            + "FROM tb_bb_blade b "
            + "JOIN tb_bb_blade_unit bu on bu.blade_cd = b.blade_cd "
            + "JOIN tb_bb_line l on bu.unit_id = l.blade_unit_id "
            + "WHERE b.brand_cd LIKE :bladeCD ", nativeQuery = true)
    List<Object[]> searchListBlade(@Param("bladeCD") String bladeCD);

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
            " AND bu.unit_id LIKE :unitID", nativeQuery = true)
    List<Object[]> getBladeByUnitID(@Param("unitID") String unitID);
}
