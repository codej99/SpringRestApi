package com.rest.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
public class Employ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employId;
    @OneToOne
    @JoinColumn(name = "employer_id")
    private Member employer;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Member employee;

    @Builder
    public Employ(Member employer, Member employee) {
        this.employer = employer;
        this.employee = employee;
    }
}
