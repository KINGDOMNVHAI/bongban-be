package com.codewithproject.springsecurity.services.impl;

import com.codewithproject.springsecurity.dto.InsertLineProgressRequest;
import com.codewithproject.springsecurity.dto.InsertLineRequest;
import com.codewithproject.springsecurity.dto.entitydto.LineDto;
import com.codewithproject.springsecurity.entities.Line;
import com.codewithproject.springsecurity.entities.LineProgress;
import com.codewithproject.springsecurity.entities.User;
import com.codewithproject.springsecurity.repository.LineProgressRepository;
import com.codewithproject.springsecurity.repository.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LineServiceImpl {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private LineRepository lineRepo;

    @Autowired
    private LineProgressRepository lineProgressRepo;

    public List<LineDto> getListLine() {
        List<LineDto> result = new ArrayList<>();
        List<Line> listLine = lineRepo.getListLine();
        if (!listLine.isEmpty()) {
            result = listLine.stream().map(b -> {
                LineDto dto = new LineDto();
                dto.setId(b.getId());
                dto.setBladeUnitID(b.getBladeUnitID());
                dto.setPeriod(b.getPeriod());
                return dto;
            }).collect(Collectors.toList());
        }
        return result;
    }

    public void insertLine(InsertLineRequest req) {
        Line line = new Line();
        lineRepo.save(line);
    }

    public void insertLineProgress(InsertLineProgressRequest req) {
        // Check user exist
        User user = userService.getUserDetail(req.getUserEmail());
        LineProgress lineP = new LineProgress();
        lineP.setUserEmail(user.getEmail());
        lineProgressRepo.save(lineP);
    }
}
