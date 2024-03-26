package com.smhrd.ttok.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.ttok.DTO.request.user.UserLoginDTO;
import com.smhrd.ttok.DTO.request.user.UserRegisterDTO;
import com.smhrd.ttok.DTO.response.UserResponseDTO;
import com.smhrd.ttok.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        UserResponseDTO successUser = userService.register(userRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(successUser);
    }

   @PostMapping("/login")
   public ResponseEntity<String> login(@RequestBody UserLoginDTO loginDTO) {
    // 사용자 인증을 수행합니다.
    if (userService.authenticate(loginDTO)) {
        return ResponseEntity.ok("Login successful!"); // 로그인 성공
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"); // 인증 실패
    }
}

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                cookie.setPath("/login");   
                response.addCookie(cookie);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body("Logout successful!"); // 로그아웃 성공
    }

    
}
