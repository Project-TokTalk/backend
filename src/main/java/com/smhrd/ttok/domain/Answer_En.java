package com.smhrd.ttok.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="ANSWER_EN")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Answer_En {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "A_EN_IDX")
    private int id;

    @Column(name = "A_EN_ANSWER", columnDefinition = "TEXT",nullable = false)
    private String answer;

    @Column(name="A_EN_COUNT", nullable = false)
    private int count;
}
