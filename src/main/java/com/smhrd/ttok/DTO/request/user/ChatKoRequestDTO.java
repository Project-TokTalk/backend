package com.smhrd.ttok.DTO.request.user;

import java.time.LocalDate;

import com.smhrd.ttok.domain.Chatting_Ko;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatKoRequestDTO {
    private int id;
    private String question;
    private String answer;
    private long user;
    private String phone;
    private LocalDate time;

    @Builder
    public ChatKoRequestDTO(int id, String question, String answer, long user, String phone, LocalDate time){
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.user = user;
        this.phone = phone;
        this.time = time;
    }

    //Entity -> DTO
    public static ChatKoRequestDTO fromEntity(Chatting_Ko chatting_Ko){
        return ChatKoRequestDTO.builder()
        .id(chatting_Ko.getId())
        .question(chatting_Ko.getQuestion())
        .answer(chatting_Ko.getAnswer().getAnswer())
        .user(chatting_Ko.getUser().getId())
        .phone(chatting_Ko.getUser().getPhone())
        .time(chatting_Ko.getTime())
        .build();
    }
}
