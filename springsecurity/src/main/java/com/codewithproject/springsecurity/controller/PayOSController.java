package com.codewithproject.springsecurity.controller;

import com.codewithproject.springsecurity.dto.response.MomoTransactionReportResponse;
import com.codewithproject.springsecurity.dto.response.PayOSTransactionReportResponse;
import com.codewithproject.springsecurity.dto.response.ThirdPartyAuthResponse;
import com.codewithproject.springsecurity.model.ResponseModel;
import com.codewithproject.springsecurity.services.impl.PayOSServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/public/payos/transaction-report")
    public PayOSTransactionReportResponse payosTransactionReport() {
        PayOSTransactionReportResponse result = new PayOSTransactionReportResponse();
        ThirdPartyAuthResponse response = payOSServiceImpl.login();
        if (!response.getToken().isEmpty()) {
            result = payOSServiceImpl.transactionReport(response.getToken());
        }
        return result;
    }


}