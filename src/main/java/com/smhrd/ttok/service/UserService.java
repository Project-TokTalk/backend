package com.smhrd.ttok.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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

    public UserResponseDTO register(UserRegisterDTO registerDTO){
        isExistUserPhone(registerDTO.getPhone());

        User saveUser = userRepository.save(UserRegisterDTO.ofEntity(registerDTO));
        log.info(saveUser.toString());
        
        return UserResponseDTO.fromEntity(saveUser);

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
