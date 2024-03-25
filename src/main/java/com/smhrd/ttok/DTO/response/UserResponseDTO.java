package com.smhrd.ttok.DTO.response;

import com.smhrd.ttok.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDTO {
    
    private String phone;
    private String name;

    @Builder
    public UserResponseDTO(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }

    //Entity -> DTO
    public static UserResponseDTO fromEntity(User user) {
        return UserResponseDTO.builder()
                .phone(user.getPhone())
                .name(user.getName())
                .build();
    }
    
}
