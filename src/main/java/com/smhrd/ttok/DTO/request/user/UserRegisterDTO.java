package com.smhrd.ttok.DTO.request.user;

import com.smhrd.ttok.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterDTO {

    private String phone;
    private String password;
    private String name;
    private String nation;
    private String age;
    private String gender;
    private boolean start;

    @Builder
    public UserRegisterDTO(String phone, String password, String name, String nation, String age, String gender,boolean start) {
        this.phone = phone;
        this.password = password;
        this.name = name;
        this.nation = nation;
        this.age = age;
        this.gender = gender;
        this.start = start;
  
    }

    // DTO -> Entity
    public static User ofEntity(UserRegisterDTO dto) {
        return User.builder()
               .phone(dto.getPhone())
               .password(dto.getPassword())
               .name(dto.getName())
               .nation(dto.getNation())
               .age(dto.getAge())
               .gender(dto.getGender())
               .start(dto.isStart())
               .build();    
    }
    
}
