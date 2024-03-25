package com.smhrd.ttok.DTO.request.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateDTO {

    private String password;
    private String name;

    @Builder
    public UserUpdateDTO(String password, String name) {
        this.password = password;
        this.name = name;
    }
    
}
