package com.smhrd.ttok.domain;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="CHATTING_KO")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chatting_Ko {
    
    @Id
    @Column(name="C_KO_IDX")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "C_KO_Q", columnDefinition = "TEXT",nullable = false)
    private String question;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="C_KO_A_IDX", nullable = false)
    private Answer_Ko answer;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="C_KO_USER_IDX")
    private User user;

    @Column(name="C_KO_TIME",nullable = false)
    @CreationTimestamp
    private LocalDate time=LocalDate.now();

}
