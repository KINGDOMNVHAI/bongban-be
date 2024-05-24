package com.codewithproject.springsecurity.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PayOSPaymentResponse {

    private String id; // Mô tả thanh toán

    private Integer orderCode; // Mã đơn hàng từ cửa hàng

    private String description; // Mô tả thanh toán

    private String status; // Trạng thái link thanh toán

    private Integer amount; // Số tiền thanh toán

    private Integer amountPaid; // Số tiền thanh toán

    private Integer amountRemaining; // Số tiền thanh toán

    private Date createdAt;

    private PayOSTransactionResponse transactions;

    private String cancellationReason; // Mã định danh ngân hàng (thường gọi là BIN)

    private Date canceledAt;




//    private String accountNumber; // Số tài khoản ngân hàng thụ hưởng, là số tài khoản ảo nếu Cổng thanh toán liên kết với VietQR PRO
//
//    private String accountName; // Tên tài khoản ngân hàng
//
//    private String currency; // Đơn vị tiền tệ
//
//    private String paymentLinkId; // Mã link thanh toán
//
//    private String checkoutUrl; // Link thanh toán
//
//    private String qrCode; // Mã VietQR dạng text
}
