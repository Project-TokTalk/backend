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
@Table(name="tb_user")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "user_phone",nullable = false)
    private String phone;

    @Column(name = "user_pw",nullable = false)
    private String password;

    @Column(name = "user_nick",nullable = false)
    private String name;

    @Column(name = "user_nation",nullable = false)
    private String nation;

    @Column(name = "user_birth",nullable = false)
    private String birth;

    @Column(name = "user_gender",nullable = false)
    private String gender;

    @Column(name = "user_start",nullable = false)
    private boolean start;

    @Column(name = "join_dt",nullable = false)
    @CreationTimestamp
    private LocalDate join_dt = LocalDate.now();
    
    
}
