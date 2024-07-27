package com.codewithproject.springsecurity.services.impl;

import com.codewithproject.springsecurity.dto.entitydto.LineDto;
import com.codewithproject.springsecurity.dto.request.InsertLineRequest;
import com.codewithproject.springsecurity.entities.Line;
import com.codewithproject.springsecurity.entities.LineProgress;
import com.codewithproject.springsecurity.entities.LineProgressCompositeKey;
import com.codewithproject.springsecurity.entities.User;
import com.codewithproject.springsecurity.repository.LineProgressRepository;
import com.codewithproject.springsecurity.repository.LineRepository;
import com.codewithproject.springsecurity.seeder.LineSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.codewithproject.springsecurity.config.MessageConstants.*;

@Service
public class LineServiceImpl {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private LineSeeder lineSeeder;

    @Autowired
    private LineRepository lineRepo;

    @Autowired
    private LineProgressRepository lineProgressRepo;

    public void truncateLine() {
        lineRepo.truncateTable();
    }

    public List<Line> seederLine() {
        List<Line> result = new ArrayList<>();
        result = lineSeeder.seederLine();
        return result;
    }

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

    public Map<String, Object> registerToLine(InsertLineRequest req) {
        Map<String, Object> result = new HashMap<>();

        String userEmail = req.getUserEmail();
        String token = req.getToken();
        if (token.isEmpty() || userEmail.isEmpty()) {
            result.put(MAP_STATUS_CODE, 404);
            result.put(MAP_MESSAGE, MESS_EMAIL_NOT_FOUND);
            return result;
        }
        // Check user exist
        Optional<User> user = userService.getUserDetail(req.getUserEmail());

        // Check line exist
        if (user.isPresent()) {
            LineProgress lineP = new LineProgress();
            LineProgressCompositeKey linePCompositeKey = new LineProgressCompositeKey();
            linePCompositeKey.setLineID(req.getLineID());
            linePCompositeKey.setUserEmail(user.get().getEmail());

            // Insert to line_progress
            lineP.setId(linePCompositeKey);
            lineP.setDepositStatus(req.getDepositStatus());
            lineProgressRepo.save(lineP);

            result.put(MAP_STATUS_CODE, 400);
            result.put(MAP_MESSAGE, MESS_SAVE_SUCCESS);
            return result;
        }
        result.put(MAP_STATUS_CODE, 200);
        result.put(MAP_MESSAGE, MESS_SAVE_FAILED);
        return result;
    }

    public Integer countByBladeCD(String lineID) {
        Integer result = lineProgressRepo.countByBladeCD(lineID);
        if (result != null) {
            return result;
        }
        return 0;
    }
}
