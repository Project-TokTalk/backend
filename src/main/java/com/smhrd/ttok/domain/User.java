package com.smhrd.ttok.domain;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// 손승아, tb_user 만드는 엔티티 생성, 20240316
@Entity
@Table(name="USER")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "USER_IDX")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "USER_PHONE",nullable = false)
    private String phone;

    @Column(name = "USER_PW",nullable = false)
    private String password;

    @Column(name = "USER_NICK",nullable = false)
    private String name;

    @Column(name = "USER_NATION",nullable = false)
    private String nation;

    @Column(name = "USER_AGE",nullable = false)
    private String age;

    @Column(name = "USER_GENDER",nullable = false)
    private String gender;

    @Column(name = "USER_START",nullable = false)
    private boolean start;

    @Column(name = "JOIN_DT",nullable = false)
    @CreationTimestamp
    private LocalDate join_dt = LocalDate.now();
    
    
}
