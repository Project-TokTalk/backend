package com.smhrd.ttok.DTO.request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserGenderDTO {
    private String gender;
    private long userCount;
    private double userPercentage;
}
