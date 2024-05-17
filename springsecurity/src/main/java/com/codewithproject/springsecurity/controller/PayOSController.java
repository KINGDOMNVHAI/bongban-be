package com.codewithproject.springsecurity.controller;

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



    // https://my.payos.vn/login
    // huynhan007@gmail.com
    // Payos4789!
    // f859ce0c1893c53f5b644965885183e21b251490fa0f8f67b4aa536def860cd8b960580f00fb7d3a13ef110a336869f3a89daea2f4970badc77a6b2c7d2e5be6
}
