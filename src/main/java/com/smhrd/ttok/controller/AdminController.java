package com.smhrd.ttok.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.ttok.DTO.request.user.UserGenderDTO;
import com.smhrd.ttok.DTO.request.user.UserNationDTO;
import com.smhrd.ttok.service.UserService;

// 손승아, 대시보드 엔드포인트 따로 관리 위해 Controller 생성, 20240401
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    // @GetMapping("/country")
    // public List<UserNationDTO> getCountryGraphData(){
    //     return userService.getCountryGraphData();
    // }
    
    // @GetMapping("/gender")
    // public List<UserGenderDTO> getGenderGraphData(){
    //     return userService.getGenderGraphData();
    // }

    @GetMapping("/{type}")
    public Object getGraphData(@PathVariable String type){
        switch (type) {
            case "country":
                return userService.getCountryGraphData();
            case "gender":
                return userService.getGenderGraphData();
            default:
                // 지원하지 않는 요청 유형에 대한 처리
                return "Invalid request type";
        }
    }
}
