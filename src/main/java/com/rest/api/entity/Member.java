package com.rest.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;
    @Column(name = "email", unique = true)
    private String email;
    private String name;
    private Date createDate;

    @Builder
    public Member(String email, String name) {
        this.email = email;
        this.name = name;
        this.createDate = new Date();
    }
}
