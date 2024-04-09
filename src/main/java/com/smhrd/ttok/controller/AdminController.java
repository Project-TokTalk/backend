package com.smhrd.ttok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.ttok.service.ChattingService;
import com.smhrd.ttok.service.UserService;

import lombok.extern.log4j.Log4j2;

// 손승아, 대시보드 엔드포인트 따로 관리 위해 Controller 생성, 20240401
@RestController
@Log4j2
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private ChattingService chattingService;

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
            case "age":
                return userService.getStartAndAgeCount();
            case "member":
                return userService.getMemberList();
            case "date":
                return userService.getDateGraphData();
            case "kochat":
                return chattingService.chatTolistK();
            case "enchat":
                return chattingService.chatTolistE();
            default:
                // 지원하지 않는 요청 유형에 대한 처리
                return "Invalid request type";
        }
    }
}
