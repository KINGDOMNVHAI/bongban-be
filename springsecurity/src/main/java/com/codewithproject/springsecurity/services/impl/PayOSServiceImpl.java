package com.codewithproject.springsecurity.services.impl;

import com.codewithproject.springsecurity.dto.ResponseDto;
import com.codewithproject.springsecurity.dto.request.PayOSCreatePaymentRequest;
import com.codewithproject.springsecurity.dto.response.PayOSPaymentResponse;
import com.codewithproject.springsecurity.dto.response.PayOSTransactionResponse;
import com.codewithproject.springsecurity.dto.response.ThirdPartyAuthResponse;
import com.codewithproject.springsecurity.entities.Payment;
import com.codewithproject.springsecurity.repository.PaymentRepository;
import com.codewithproject.springsecurity.repository.TransactionRepository;
import com.codewithproject.springsecurity.util.ApiUtil;
import com.codewithproject.springsecurity.util.ArrayUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.codec.digest.HmacUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PayOSServiceImpl {

    @Autowired
    private PaymentRepository repoPayment;

    @Autowired
    private TransactionRepository repoTransaction;

    static String clientID = "a8882de3-0fa0-4645-b557-bfc870990f2c";
    static String apiKey = "ff0703c5-b92a-4c8d-bacc-f738f3c88ea8";
    static String checksumKey = "156410ddd340172ed963556faec4bf08c09ebaa22098ea8e79a1cbdea27e0422";
    static String reqSignature = "amount=$amount&cancelUrl=$cancelUrl&description=$description&orderCode=$orderCode&returnUrl=$returnUrl";

    static String transaction = "{\n" +
            "  \"orderCode\":123,\n" +
            "  \"amount\":3000,\n" +
            "  \"description\": \"VQRIO123\",\n" +
            "  \"accountNumber\":\"12345678\",\n" +
            "  \"reference\":\"TF230204212323\",\n" +
            "  \"transactionDateTime\":\"2023-02-04 18:25:00\",\n" +
            "  \"currency\":\"VND\",\n" +
            "  \"paymentLinkId\":\"124c33293c43417ab7879e14c8d9eb18\",\n" +
            "  \"code\":\"00\",\n" +
            "  \"desc\":\"Thành công\",\n" +
            "  \"counterAccountBankId\":\"\",\n" +
            "  \"counterAccountBankName\":\"\",\n" +
            "  \"counterAccountName\":\"\",\n" +
            "  \"counterAccountNumber\":\"\",\n" +
            "  \"virtualAccountName\":\"\",\n" +
            "  \"virtualAccountNumber\":\"\"\n" +
            "  \n" +
            "}";

//    {
//        "orderCode":123,
//            "amount":3000,
//            "description": "VQRIO123",
//            "accountNumber":"12345678",
//            "reference":"TF230204212323",
//            "transactionDateTime":"2023-02-04 18:25:00",
//            "currency":"VND",
//            "paymentLinkId":"124c33293c43417ab7879e14c8d9eb18",
//            "code":"00",
//            "desc":"Thành công",
//            "counterAccountBankId":"",
//            "counterAccountBankName":"",
//            "counterAccountName":"",
//            "counterAccountNumber":"",
//            "virtualAccountName":"",
//            "virtualAccountNumber":""
//
//    }

    static String transactionSignature = "412e915d2871504ed31be63c8f62a149a4410d34c4c42affc9006ef9917eaa03";

    /*
    * PayOS: huynhan0007@gmail.com / Payos4789!
    * */
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

    public PayOSTransactionResponse transactionReport(String bearerToken) {
        PayOSTransactionResponse payosResponse = new PayOSTransactionResponse();
        String url = "https://api-app.payos.vn/organizations/b8ab7e3a133811ef915f0242ac110002/statistics/payment-link?typeOrder=PAID&page=0&pageSize=20";
        ResponseEntity<String> responseEntity = ApiUtil.callPostApiBearerToken(bearerToken, url);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            // Get data
            Gson gson = new GsonBuilder()
                    .serializeNulls() // Include null values during serialization
                    .create();
            ResponseDto dataDto = ApiUtil.mapToDTO(responseEntity, ResponseDto.class);
            Map<String,Object> dataMap = dataDto.getData();
            List<Object> list = ArrayUtil.convertMapValuesToList(dataMap);
            if (!list.isEmpty()) {
                // Convert to PayOS response
                String strObj = gson.toJson(dataMap.get("orders"));
                List<PayOSTransactionResponse> payos = convertJsonToList(strObj);
//                assert payos != null;
//                if (!payos.isEmpty()) {
//                    payosResponse = payos.get(0);
//                }
            }
        }
        return payosResponse;
    }

    private static List<PayOSTransactionResponse> convertJsonToList(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, new TypeReference<List<PayOSTransactionResponse>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PayOSPaymentResponse> paymentListReport() {
        List<PayOSPaymentResponse> payosResponse = new ArrayList<>();
        List<Payment> listPayment = repoPayment.getListPayment();
        if (!listPayment.isEmpty()) {
            payosResponse = listPayment.stream().map(p -> {
                PayOSPaymentResponse dto = new PayOSPaymentResponse();
//                dto.setId(p.getId());
//                dto.setIdPayment(p.getIdPayment());
//                dto.setPlatform(p.getPlatform());
                dto.setDescription(p.getDescription());
                dto.setAmount(p.getAmount());
//                dto.setAmountPaid(p.getAmountPaid());
//                dto.setAmountRemaining(p.getAmountRemaining());
//                dto.setStatus(p.getStatus());

                PayOSTransactionResponse trans = new PayOSTransactionResponse();
//                dto.setTransactions(trans);
                return dto;
            }).collect(Collectors.toList());
        }
        return payosResponse;
    }

    public PayOSPaymentResponse paymentRequest(PayOSCreatePaymentRequest req, String authorizationHeader) {
        PayOSPaymentResponse payosResponse = new PayOSPaymentResponse();
        // Create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Create transaction string
        String transStr = createTransactionStr(req.getAmount(), req.getCancelUrl(), req.getDescription()
                , req.getOrderCode(), req.getReturnUrl());
        System.out.println(transStr);

        // Create signature
        String signature = new HmacUtils("HmacSHA256", checksumKey).hmacHex(transStr);
        System.out.println(signature);

        // Set the request URL and create the request body
        String url = "https://api-merchant.payos.vn/v2/payment-requests?x-client-id=" + clientID + "&x-api-key=" + apiKey;
        String reqBody = "{\n" +
                "  \"orderCode\": 11,\n" +
                "  \"amount\": 3000,\n" +
                "  \"description\": " + req.getDescription() + ",\n" +
                "  \"buyerName\": \"Nguyen Van A\",\n" +
                "  \"buyerEmail\": \"buyer-email@gmail.com\",\n" +
                "  \"buyerPhone\": \"090xxxxxxx\",\n" +
                "  \"buyerAddress\": \"số nhà, đường, phường, tỉnh hoặc thành phố\",\n" +
                "  \"items\": [\n" +
                "    {\n" +
                "      \"name\": \"Vot bong ban \",\n" +
                "      \"quantity\": 1,\n" +
                "      \"price\": 3000000\n" +
                "    }\n" +
                "  ],\n" +
                "  \"cancelUrl\": \"abc\",\n" +
                "  \"returnUrl\": \"abc\",\n" +
                "  \"signature\": " + signature + "\n" +
                "}";



        ResponseEntity<String> response = ApiUtil.callPostApiHaveBodyAuthor(authorizationHeader, reqBody, url);

//        if (responseEntity.getStatusCode().is2xxSuccessful()) {
//            System.out.println("Done");
//        }




        // Make the POST request
//        String jsonString = restTemplate.postForObject(url, reqBody, String.class);
        System.out.println(response);

        payosResponse.setAmount(req.getAmount());
        payosResponse.setDescription(req.getDescription());
        payosResponse.setOrderCode(req.getOrderCode());
        payosResponse.setCurrency("VND");
        payosResponse.setSignature(signature);
        return payosResponse;
    }

    private String createTransactionStr(int amount, String cancelUrl, String description, int orderCode, String returnUrl) {
//        amount=$amount&cancelUrl=$cancelUrl&description=$description&orderCode=$orderCode&returnUrl=$returnUrl
        StringBuilder transactionStr = new StringBuilder();
        transactionStr.append("amount=");
        transactionStr.append(amount);
        transactionStr.append("&cancelUrl=");
        transactionStr.append(cancelUrl);
        transactionStr.append("&description=");
        transactionStr.append(description);
        transactionStr.append("&orderCode=");
        transactionStr.append(orderCode);
        transactionStr.append("&returnUrl=");
        transactionStr.append(returnUrl);
        return transactionStr.toString();
    }









    public PayOSPaymentResponse paymentReport(String id) {
        PayOSPaymentResponse payosResponse = new PayOSPaymentResponse();
        System.out.println(isValidData(transaction, transactionSignature));
        return payosResponse;
    }

    public static String isValidData(String transaction, String transactionSignature) {
        try {
            JSONObject jsonObject = new JSONObject(transaction);
            Iterator<String> sortedIt = sortedIterator(jsonObject.keys(), (a, b) -> a.compareTo(b));

            StringBuilder transactionStr = new StringBuilder();
            while (sortedIt.hasNext()) {
                String key = sortedIt.next();
                String value = jsonObject.get(key).toString();
                transactionStr.append(key);
                transactionStr.append('=');
                transactionStr.append(value);
                if (sortedIt.hasNext()) {
                    transactionStr.append('&');
                }
            }
            System.out.println("transactionStr");
            System.out.println(transactionStr);

            return new HmacUtils("HmacSHA256", checksumKey).hmacHex(transactionStr.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Iterator<String> sortedIterator(Iterator<?> it, Comparator<String> comparator) {
        List<String> list = new ArrayList<String>();
        while (it.hasNext()) {
            list.add((String) it.next());
        }

        Collections.sort(list, comparator);
        return list.iterator();
    }


    // https://my.payos.vn/login
    // huynhan007@gmail.com
    // Payos4789!
}
