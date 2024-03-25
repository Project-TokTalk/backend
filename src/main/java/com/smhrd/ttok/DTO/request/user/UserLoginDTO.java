package com.smhrd.ttok.DTO.request.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginDTO {

    private String phone;
    private String password;

    @Builder
    public UserLoginDTO(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }
    
}
