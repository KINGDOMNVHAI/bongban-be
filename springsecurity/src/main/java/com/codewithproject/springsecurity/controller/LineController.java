package com.codewithproject.springsecurity.controller;

import com.codewithproject.springsecurity.dto.request.InsertLineRequest;
import com.codewithproject.springsecurity.dto.entitydto.LineDto;
import com.codewithproject.springsecurity.services.impl.LineServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/public/line/list")
    public List<LineDto> getListLine() {
        return lineServiceImpl.getListLine();
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
