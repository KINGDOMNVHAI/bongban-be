package com.codewithproject.springsecurity.services.impl;

import com.codewithproject.springsecurity.dto.entitydto.LineProgressDto;
import com.codewithproject.springsecurity.dto.request.InsertLineRequest;
import com.codewithproject.springsecurity.dto.request.SearchLineRequest;
import com.codewithproject.springsecurity.dto.response.LineListResponse;
import com.codewithproject.springsecurity.entities.Line;
import com.codewithproject.springsecurity.entities.LineProgress;
import com.codewithproject.springsecurity.entities.LineProgressCompositeKey;
import com.codewithproject.springsecurity.entities.User;
import com.codewithproject.springsecurity.repository.LineProgressRepository;
import com.codewithproject.springsecurity.repository.LineRepository;
import com.codewithproject.springsecurity.seeder.LineSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.codewithproject.springsecurity.config.MessageConstants.MAP_MESSAGE;
import static com.codewithproject.springsecurity.config.MessageConstants.MAP_STATUS_CODE;
import static com.codewithproject.springsecurity.config.MessageConstants.MESS_EMAIL_NOT_FOUND;
import static com.codewithproject.springsecurity.config.MessageConstants.MESS_SAVE_FAILED;
import static com.codewithproject.springsecurity.config.MessageConstants.MESS_SAVE_SUCCESS;

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

    public List<LineListResponse> searchListLine(SearchLineRequest req) {
        List<LineListResponse> response = new ArrayList<>();
        List<Line> listLine = lineRepo.getLineHaveProgress();
        if (!listLine.isEmpty()) {
            // get line id
            List<String> listLineID = listLine.stream().distinct().map(Line::getId).toList();
            List<LineProgress> listLinePro = lineProgressRepo.getLineProgressByID(listLineID);
            if (!listLinePro.isEmpty()) {

                List<LineProgressDto> lpDto = listLinePro.stream().map(p -> {
                    LineProgressDto dto = new LineProgressDto();
                    dto.setLineID(p.getId().getLineID());
                    dto.setUserEmail(p.getId().getUserEmail());
                    dto.setDepositStatus(p.getDepositStatus());
                    dto.setName(userService.getUserFullname(p.getId().getUserEmail()));
                    return dto;
                }).toList();

                response = listLine.stream().map(b -> {
                    LineListResponse dto = new LineListResponse();
                    dto.setId(b.getId());
                    dto.setBladeUnitID(b.getBladeUnitID());
                    dto.setPeriodCnt(b.getPeriodCnt());
                    dto.setPeriod(b.getPeriod());
                    dto.setInitPrice(b.getInitPrice());
                    dto.setDeposit(b.getDeposit());
                    dto.setDepreciation(b.getDepreciation());
                    dto.setFee(b.getFee());
                    dto.setEndPrice(b.getEndPrice());
                    List<LineProgressDto> listDto = lpDto.stream().filter(p -> p.getLineID().equals(dto.getId())).toList();
                    dto.setListProgress(listDto);
                    dto.setCountListProgress(listDto.size());

                    // get LineProgressDto of email
                    List<LineProgressDto> userLineProgress = lpDto.stream()
                            .filter(lp -> lp.getUserEmail().equals(req.getEmail()))
                            .map(lp -> {
                                LineProgressDto userLine = new LineProgressDto();
                                userLine.setLineID(lp.getLineID());
                                userLine.setUserEmail(lp.getUserEmail());
                                userLine.setDepositStatus(lp.getDepositStatus());
                                return userLine;
                            }).toList();

                    if (!userLineProgress.isEmpty()) {
                        dto.setUserLineProgress(userLineProgress.get(0));
                    }

                    return dto;
                }).collect(Collectors.toList());
            }
        }
        return response;
    }

    private List<LineProgress> check(String id, List<LineProgress> listLinePro) {
        List<LineProgress> lineProgress = new ArrayList<>();
        LineProgress lineP = new LineProgress();
        LineProgressCompositeKey linePCompositeKey = new LineProgressCompositeKey();
        linePCompositeKey.setLineID(id);

        return lineProgress;
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

        String email = req.getEmail();
        String token = req.getToken();
        if (token.isEmpty() || email.isEmpty()) {
            result.put(MAP_STATUS_CODE, 404);
            result.put(MAP_MESSAGE, MESS_EMAIL_NOT_FOUND);
            return result;
        }
        // Check user exist
        Optional<User> user = userService.getUserDetail(req.getEmail());

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
