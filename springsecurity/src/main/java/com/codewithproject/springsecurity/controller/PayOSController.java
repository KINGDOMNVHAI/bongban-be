package com.codewithproject.springsecurity.controller;

import com.codewithproject.springsecurity.dto.response.PayOSPaymentResponse;
import com.codewithproject.springsecurity.dto.response.PayOSTransactionResponse;
import com.codewithproject.springsecurity.dto.response.ThirdPartyAuthResponse;
import com.codewithproject.springsecurity.services.impl.PayOSServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PayOSController {

    @Autowired
    private PayOSServiceImpl payOSServiceImpl;

    @GetMapping("/public/payos/login")
    public ThirdPartyAuthResponse payosLogin() {
        ThirdPartyAuthResponse response = new ThirdPartyAuthResponse();
        response = payOSServiceImpl.login();
        return response;
    }

    @GetMapping("/public/payos/transaction-report/list")
    public PayOSTransactionResponse payosListTransactionReport() {
        PayOSTransactionResponse result = new PayOSTransactionResponse();
        ThirdPartyAuthResponse response = payOSServiceImpl.login();
        if (!response.getToken().isEmpty()) {
            result = payOSServiceImpl.transactionReport(response.getToken());
        }
        return result;
    }

    @GetMapping("/public/payos/payment-report/list")
    public List<PayOSPaymentResponse> payosListPaymentReport() {
        List<PayOSPaymentResponse> result = new ArrayList<>();
//        ThirdPartyAuthResponse response = payOSServiceImpl.login();
//        if (!response.getToken().isEmpty()) {
        result = payOSServiceImpl.paymentListReport();
//        }
        return result;
    }

    @GetMapping("/public/payos/payment-report/{id}")
    public PayOSPaymentResponse payosPaymentReport(@PathVariable String id) {
        PayOSPaymentResponse result = new PayOSPaymentResponse();
//        ThirdPartyAuthResponse response = payOSServiceImpl.login();
//        if (!response.getToken().isEmpty()) {
            result = payOSServiceImpl.paymentReport(id);
//        }
        return result;
    }
}
