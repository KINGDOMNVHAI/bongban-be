package com.codewithproject.springsecurity.services.impl;

import com.codewithproject.springsecurity.dto.ResponseDto;
import com.codewithproject.springsecurity.dto.response.ThirdPartyAuthResponse;
import com.codewithproject.springsecurity.util.ApiUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;

@Service
public class PayOSServiceImpl {

    public ThirdPartyAuthResponse login() {
        ThirdPartyAuthResponse result = new ThirdPartyAuthResponse();
        // Set the request URL and create the request body
        String url = "https://api-app.payos.vn/auth/sign-in";
        String reqBody = "{\"email\": \"huynhan007@gmail.com\",\"password\": \"f859ce0c1893c53f5b644965885183e21b251490fa0f8f67b4aa536def860cd8b960580f00fb7d3a13ef110a336869f3a89daea2f4970badc77a6b2c7d2e5be6\"}";

        try {
            // Make the POST request
            String jsonString = ApiUtil.callPostApi(url, reqBody, String.class);
            System.out.println(jsonString);

            // Convert the JSON string to a Map<String, Object>
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseDto dataDto = objectMapper.readValue(jsonString, new TypeReference<ResponseDto>() {});
            Map<String,Object> dataMap = dataDto.getData();
            result.setToken(dataMap.get("token").toString());
            result.setRefreshToken(dataMap.get("refreshToken").toString());
            result.setEmail(dataMap.get("email").toString());
            result.setFirst_name(dataMap.get("first_name").toString());
            result.setLast_name(dataMap.get("last_name").toString());
        } catch (HttpClientErrorException.Unauthorized ex) {
            // Handle 401 Unauthorized error
            System.err.println("Unauthorized: Wrong username or password");
        } catch (Exception ex) {
            // Handle other exceptions
            ex.printStackTrace();
        }
        return result;
    }
}
