package com.codewithproject.springsecurity.repository;

import com.codewithproject.springsecurity.entities.BladeUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BladeUnitRepository extends JpaRepository<BladeUnit, Long> {



}
