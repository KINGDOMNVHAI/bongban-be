package com.codewithproject.springsecurity.services.impl;

import com.codewithproject.springsecurity.dto.request.InsertLineRequest;
import com.codewithproject.springsecurity.dto.entitydto.LineDto;
import com.codewithproject.springsecurity.entities.Line;
import com.codewithproject.springsecurity.entities.LineProgress;
import com.codewithproject.springsecurity.entities.LineProgressCompositeKey;
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
        line.setId(req.getId());
        line.setBladeUnitID(req.getBladeUnitID());
        line.setPeriodCnt(req.getPeriodCnt());
        line.setPeriod(req.getPeriod());
        line.setInitPrice(req.getInitPrice());
        line.setDeposit(req.getDeposit());
        line.setDepreciation(req.getDepreciation());
        line.setFee(req.getFee());
        line.setEndPrice(req.getEndPrice());
        lineRepo.save(line);
    }

    public String insertLineProgress(InsertLineRequest req) {
        // Check user exist
        Optional<User> user = userService.getUserDetail(req.getUserEmail());
        // Check line exist

        if (user.isPresent()) {
            LineProgress lineP = new LineProgress();
            LineProgressCompositeKey linePCompositeKey = new LineProgressCompositeKey();
            linePCompositeKey.setLineID(req.getLineID());
            linePCompositeKey.setUserEmail(user.get().getEmail());

            lineP.setId(linePCompositeKey);
            lineP.setDepositStatus(req.getDepositStatus());
            lineProgressRepo.save(lineP);
            return "Success";
        }
        return "Fail";
    }
}
