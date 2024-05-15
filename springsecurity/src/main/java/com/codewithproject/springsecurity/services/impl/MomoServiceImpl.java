package com.codewithproject.springsecurity.services.impl;

import com.codewithproject.springsecurity.dto.MomoLoginDataDto;
import com.codewithproject.springsecurity.dto.response.MomoTransactionReportResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Map;

@Service
public class MomoServiceImpl {

    public String login() {
        String token = "";

        // Create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Set the request URL and create the request body
        String url = "https://business.momo.vn/api/authentication/login?language=vi";
        String reqBody = "{\"username\": \"1m1kme\", \"password\": \"Momo4789!\"}";

        try {
            // Make the POST request
            String jsonString = restTemplate.postForObject(url, reqBody, String.class);

            // Convert the JSON string to a Map<String, Object>
            ObjectMapper objectMapper = new ObjectMapper();
            MomoLoginDataDto dataDto = objectMapper.readValue(jsonString, new TypeReference<MomoLoginDataDto>() {});
            Map<String,Object> dataMap = dataDto.getData();
            token = dataMap.get("token").toString();

        } catch (HttpClientErrorException.Unauthorized ex) {
            // Handle 401 Unauthorized error
            System.err.println("Unauthorized: Wrong username or password");
        } catch (Exception ex) {
            // Handle other exceptions
            ex.printStackTrace();
        }

//        String result = restTemplate.getForObject(url, String.class);

        return token;
    }

    public MomoTransactionReportResponse transactionReport(String token) {
        MomoTransactionReportResponse response = new MomoTransactionReportResponse();

        System.out.println(token);

        return response;
    }
}
