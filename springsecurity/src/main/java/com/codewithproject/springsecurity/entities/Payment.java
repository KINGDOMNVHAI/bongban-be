package com.codewithproject.springsecurity.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_bb_payment")
public class Payment {

    @Id
    @Column(name = "id")
    private String id; // Mô tả thanh toán

    @Column(name = "orderCode")
    private Integer orderCode; // Mã đơn hàng từ cửa hàng

    @Column(name = "description")
    private String description; // Mô tả thanh toán

    @Column(name = "status")
    private String status; // Trạng thái link thanh toán

    @Column(name = "amount")
    private Integer amount; // Số tiền thanh toán

    @Column(name = "amountPaid")
    private Integer amountPaid; // Số tiền thanh toán

    @Column(name = "amountRemaining")
    private Integer amountRemaining; // Số tiền thanh toán

    @Column(name = "createdAt")
    private Date createdAt;
}
