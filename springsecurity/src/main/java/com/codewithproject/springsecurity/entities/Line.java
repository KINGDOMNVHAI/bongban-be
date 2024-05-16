package com.codewithproject.springsecurity.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_bb_line")
public class Line {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "blade_unit_id", nullable = false)
    private String bladeUnitID;

    @Column(name = "period_cnt")
    private Integer periodCnt;

    @Column(name = "period")
    private String period;

    @Column(name = "init_price")
    private Integer initPrice;

    @Column(name = "deposit")
    private Integer deposit;

    @Column(name = "depreciation")
    private Integer depreciation;

    @Column(name = "fee")
    private Integer fee;

    @Column(name = "end_price")
    private Integer endPrice;
}
