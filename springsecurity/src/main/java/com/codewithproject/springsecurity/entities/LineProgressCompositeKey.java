package com.codewithproject.springsecurity.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class LineProgressCompositeKey {

    @Column(name = "line_id")
    private String lineID;

    @Column(name = "user_email", nullable = false)
    private String userEmail;
}
