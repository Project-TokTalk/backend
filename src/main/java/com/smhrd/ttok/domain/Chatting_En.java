package com.smhrd.ttok.domain;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="CHATTING_EN")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chatting_En {
    
    @Id
    @Column(name="C_EN_IDX")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "C_EN_Q", columnDefinition = "TEXT",nullable = false)
    private String question;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="C_EN_A_IDX", nullable = false)
    private Answer_En answer;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="C_EN_USER_IDX")
    private User user;

    @Column(name="C_EN_TIME",nullable = false)
    private LocalDate time=LocalDate.now();
}
