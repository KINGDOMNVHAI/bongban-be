package com.codewithproject.springsecurity.repository;

import com.codewithproject.springsecurity.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value = "SELECT p.* "
            + " FROM tb_bb_payment p ", nativeQuery = true)
    List<Payment> getListPayment();
}
