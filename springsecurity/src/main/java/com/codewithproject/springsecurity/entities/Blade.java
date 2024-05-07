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
@Table(name = "tb_bb_blade")
public class Blade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq", nullable = false)
    private Integer seq;

    @Column(name = "brand_cd", nullable = false)
    private String brandCD;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "blade_cd")
    private String bladeCD;

    @Column(name = "blade_name")
    private String bladeName;

    @Column(name = "blade_full_name")
    private String bladeFullName;

    @Column(name = "sub_branch")
    private String subBranch;

    @Column(name = "paddle_tp")
    private String paddleTP;
}
