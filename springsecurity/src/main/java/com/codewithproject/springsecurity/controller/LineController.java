package com.codewithproject.springsecurity.controller;

import com.codewithproject.springsecurity.dto.request.InsertLineRequest;
import com.codewithproject.springsecurity.dto.request.SearchLineRequest;
import com.codewithproject.springsecurity.dto.response.LineListResponse;
import com.codewithproject.springsecurity.services.impl.LineServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LineController {

    @Autowired
    private LineServiceImpl lineServiceImpl;

    @GetMapping("/truncate")
    public ResponseEntity<String> truncateTable() {
        lineServiceImpl.truncateLine();
        lineServiceImpl.seederLine();
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/public/line/list")
    public List<LineListResponse> getListLine(@RequestBody SearchLineRequest req) {
        return lineServiceImpl.searchListLine(req);
    }

    @PostMapping("/public/line/insert")
    public String insertLine(@RequestBody InsertLineRequest req) {
        lineServiceImpl.insertLine(req);
        return "success";
    }

    @PostMapping("/public/line/register")
    public Map<String,Object> registerToLine(@RequestBody InsertLineRequest req) {
        Map<String,Object> result = new HashMap<>();
        result = lineServiceImpl.registerToLine(req);

//        result.put("message", message);
        return result;
    }
}
