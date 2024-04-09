package com.smhrd.ttok.DTO.request.user;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 이주명, 회원정보 관리용 DTO 생성, 20240405
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserMemberDTO {
    private String phone;
    private String name;
    private String nation;
    private String age;
    private String gender;
    private boolean start;
    private LocalDate join_dt;
}