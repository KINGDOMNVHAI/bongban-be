package com.codewithproject.springsecurity.seeder;

import com.codewithproject.springsecurity.entities.Line;
import com.codewithproject.springsecurity.entities.LineProgress;
import com.codewithproject.springsecurity.entities.User;
import com.codewithproject.springsecurity.enums.Role;
import com.codewithproject.springsecurity.repository.LineProgressRepository;
import com.codewithproject.springsecurity.repository.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LineSeeder {

    @Autowired
    private LineRepository lineRepo;

    @Autowired
    private LineProgressRepository lineProgressRepo;

    public List<Line> seederLine() {
        List<Line> result = new ArrayList<>();
        try {
            Line line1 = new Line();
            line1.setBladeUnitID("BUT_TMB_ALC_FL_00001");
            line1.setId("2024_03_00003");
            line1.setPeriodCnt(3);
            line1.setPeriod("1M");
            line1.setInitPrice(3000000);
            line1.setDeposit(300000);
            line1.setDepreciation(100000);
            line1.setFee(50000);
            line1.setEndPrice(2700000);
            lineRepo.save(line1);
            result.add(line1);

            Line line2 = new Line();
            line2.setBladeUnitID("NTK_ACS_XXX_FL_00001");
            line2.setId("2024_06_00001");
            line2.setPeriodCnt(6);
            line2.setPeriod("1M");
            line2.setInitPrice(4000000);
            line2.setDeposit(300000);
            line2.setDepreciation(100000);
            line2.setFee(50000);
            line2.setEndPrice(3700000);
            lineRepo.save(line2);
            result.add(line2);

            Line line3 = new Line();
            line3.setBladeUnitID("NTK_ACS_XXX_FL_00002");
            line3.setId("2024_12_00002");
            line3.setPeriodCnt(12);
            line3.setPeriod("2W");
            line3.setInitPrice(4000000);
            line3.setDeposit(300000);
            line3.setDepreciation(100000);
            line3.setFee(50000);
            line3.setEndPrice(3700000);
            lineRepo.save(line3);
            result.add(line3);

        } catch (Exception ex) {
            return result;
        }
        return result;
    }
}
