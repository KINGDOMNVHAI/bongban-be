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
}
