package com.smhrd.ttok.DTO.response;

import com.smhrd.ttok.domain.Answer_Ko;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnswerKoDTO {
    
    private int id;
    private String answer;
    private int count;

    @Builder
    public AnswerKoDTO(int id, String answer, int count){
        this.id = id;
        this.answer = answer;
        this.count = count;
    }

    //Entity -> DTO
    public static AnswerKoDTO fromEntity(Answer_Ko answer_Ko){
        return AnswerKoDTO.builder()
        .id(answer_Ko.getId())
        .answer(answer_Ko.getAnswer())
        .count(answer_Ko.getCount())
        .build();
    }

}
