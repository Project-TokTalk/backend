package com.smhrd.ttok.DTO.request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 임해솔 0406 월별 사용자 증가 추이 확인용 DTO 생성
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDateDTO {
    private String year_month;
    private Long count;
}
