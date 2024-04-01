package com.smhrd.ttok.DTO.request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 손승아, User에서 가져온 국가, 국가별 사용자 수, 국가별 사용자 비율 DTO 생성, 20240401
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserNationDTO {
    private String nation;
    private long userCount;
    private double userPercentage;
}
