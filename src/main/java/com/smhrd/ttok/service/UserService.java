package com.smhrd.ttok.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smhrd.ttok.DTO.request.user.UserLoginDTO;
import com.smhrd.ttok.DTO.request.user.UserRegisterDTO;
import com.smhrd.ttok.DTO.response.UserResponseDTO;
import com.smhrd.ttok.common.exception.UserException;
import com.smhrd.ttok.domain.User;
import com.smhrd.ttok.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserResponseDTO register(UserRegisterDTO registerDTO){
        isExistUserPhone(registerDTO.getPhone());

        String encodePwd = encoder.encode(registerDTO.getPassword());
        registerDTO.setPassword(encodePwd);

        User saveUser = userRepository.save(UserRegisterDTO.ofEntity(registerDTO));
        log.info(saveUser.toString());
        
        return UserResponseDTO.fromEntity(saveUser);

    }
    public boolean authenticate(UserLoginDTO loginDTO){
        String phone = loginDTO.getPhone();
        String password = loginDTO.getPassword();

        Optional<User> userOptional = userRepository.findByPhone(phone);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // BCryptPasswordEncoder를 사용하여 비밀번호를 비교합니다.
            return passwordEncoder.matches(password, user.getPassword());
        } else {
            return false; // 사용자가 존재하지 않으면 인증 실패
        }

    }
        

    /**
     * 아이디(휴대폰 번호) 중복 체크
     * @param phone
     */
    private void isExistUserPhone(String phone){
        if(userRepository.findByPhone(phone).isPresent()){
            throw new UserException("이미 존재하는 휴대폰 번호입니다.", HttpStatus.BAD_REQUEST);
        }
    }
    
}
