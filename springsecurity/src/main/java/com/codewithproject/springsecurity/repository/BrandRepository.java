package com.codewithproject.springsecurity.repository;

import com.codewithproject.springsecurity.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Query(value = "SELECT b.* "
            + " FROM tb_bb_brand b ", nativeQuery = true)
    List<Brand> getListBrand();
}
