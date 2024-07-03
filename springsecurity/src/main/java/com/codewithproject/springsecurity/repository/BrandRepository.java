package com.codewithproject.springsecurity.repository;

import com.codewithproject.springsecurity.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Query(value = "SELECT b.* "
            + " FROM tb_bb_brand b ", nativeQuery = true)
    List<Brand> getListBrand();

    @Query(value = "SELECT b.* "
            + " FROM tb_bb_brand b "
            + " WHERE b.brand_cd = :brandCd ", nativeQuery = true)
    Optional<Brand> getBrandDetail(@Param("brandCd") String brandCd);

    @Query(value = "SELECT b.* "
            + " FROM tb_bb_brand b "
            + " WHERE b.parent LIKE 'ROOT' ", nativeQuery = true)
    List<Brand> getListBrandParentRoot();

    @Query(value = "SELECT b.* "
            + " FROM tb_bb_brand b "
            + " WHERE b.parent NOT LIKE 'ROOT' "
            + " AND b.parent LIKE :brandCd ", nativeQuery = true)
    List<Brand> getListSubBrandByBrandCD(@Param("brandCd") String brandCD);

    @Query(value = "SELECT b.seq, b.brand_cd, b.brand_name, b.blade_cd, b.blade_name, b.blade_full_name "
            + ", bu.unit_id, bu.blade_cd "
            + ", l.blade_unit_id, l.period, l.period_cnt"
            + ", l.init_price, l.deposit, l.depreciation, l.fee, l.end_price "
            + "FROM tb_bb_blade b "
            + "JOIN tb_bb_blade_unit bu on bu.blade_cd = b.blade_cd "
            + "JOIN tb_bb_line l on bu.unit_id = l.blade_unit_id "
            + "WHERE b.brand_cd LIKE :bladeCD ", nativeQuery = true)
    List<Object[]> searchListBrand(@Param("bladeCD") String bladeCD);
}
