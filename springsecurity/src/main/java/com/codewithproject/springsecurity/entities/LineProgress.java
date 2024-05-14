package com.codewithproject.springsecurity.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
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
@Table(name = "tb_bb_line_progress")
public class LineProgress {

    @EmbeddedId
    private LineProgressCompositeKey id;

//    @Id
//    @Column(name = "line_id")
//    private String lineID;
//
//    @Column(name = "user_email", nullable = false)
//    private String userEmail;

    @Column(name = "deposit_status")
    private String depositStatus;
}
