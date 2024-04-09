package com.smhrd.ttok.DTO.request.user;

import java.time.LocalDate;

import com.smhrd.ttok.domain.Chatting_En;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatEnRequestDTO {
    private int id;
    private String question;
    private String answer;
    private long user;
    private LocalDate time;

    @Builder
    public ChatEnRequestDTO(int id, String question, String answer, long user, LocalDate time){
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.user = user;
        this.time = time;
    }

    //Entity -> DTO
    public static ChatKoRequestDTO fromEntity(Chatting_En chatting_En){
        return ChatKoRequestDTO.builder()
        .id(chatting_En.getId())
        .question(chatting_En.getQuestion())
        .answer(chatting_En.getAnswer().getAnswer())
        .user(chatting_En.getUser().getId())
        .time(chatting_En.getTime())
        .build();
    }
}
