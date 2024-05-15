package com.codewithproject.springsecurity.controller;

import com.codewithproject.springsecurity.dto.request.InsertLineRequest;
import com.codewithproject.springsecurity.dto.entitydto.LineDto;
import com.codewithproject.springsecurity.services.impl.LineServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LineController {

    @Autowired
    private LineServiceImpl lineServiceImpl;

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
    public String registerLine(@RequestBody InsertLineRequest req) {
        return lineServiceImpl.insertLineProgress(req);
    }
}
