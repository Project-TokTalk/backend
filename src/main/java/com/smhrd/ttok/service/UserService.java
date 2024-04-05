package com.smhrd.ttok.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smhrd.ttok.DTO.request.user.UserGenderDTO;
import com.smhrd.ttok.DTO.request.user.UserLoginDTO;
import com.smhrd.ttok.DTO.request.user.UserMemberDTO;
import com.smhrd.ttok.DTO.request.user.UserNationDTO;
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
    // 손승아, 국가 정보 가져와서 DTO 매핑 후 컨트롤러에 전달, 20240401
    public List<UserNationDTO> getCountryGraphData(){
        List<User> users = userRepository.findAll();

        // 국가별 사용자 수를 계산하기 위해 스트림과 그루핑을 사용하여 맵으로 변환합니다.
        Map<String, Long> countryCounts = users.stream()
                .collect(Collectors.groupingBy(User::getNation, Collectors.counting()));

        // 총 사용자 수 계산
        long totalUsers = users.size();

        // UserNationDTO 리스트로 변환합니다.
        List<UserNationDTO> countryDataList = countryCounts.entrySet().stream()
                .map(entry -> {
                    double percentage = (double) entry.getValue() / totalUsers * 100;
                    log.info("country: " + entry.getKey() + ", count: " + entry.getValue());
                    return new UserNationDTO(entry.getKey(), entry.getValue(), percentage);
                })
                .collect(Collectors.toList());

        return countryDataList;
    }

    //손승아, 유저 정보 가져와서 DTO 매핑 후 컨트롤러에 전달, 20240403
    public List<UserGenderDTO> getGenderGraphData(){
        List<User> users = userRepository.findAll();

        Map<String,Long> genderCounts = users.stream()
                        .collect(Collectors.groupingBy(User::getGender, Collectors.counting()));

        long totalUsers = users.size();

        List<UserGenderDTO> genderDataList = genderCounts.entrySet().stream()
                        .map(entry -> {
                            double percentage = (double) entry.getValue() / totalUsers * 100;
                            log.info("gender: " + entry.getKey() + ", count: " + entry.getValue());
                            return new UserGenderDTO(entry.getKey(), entry.getValue(), percentage);
                        })
                        .collect(Collectors.toList());

            return genderDataList;
    }
    
    // 손승아, 연령대 별 정보 가져와서 DTO 매핑 후 컨트롤러 전달, 20240404
    public Map<String, Map<String, Long>> getStartAndAgeCount() {
        List<Object[]> startTrueResults = userRepository.countStartTrueByAgeGroup();
        List<Object[]> totalResults = userRepository.countByAgeGroup();
        
        Map<String, Map<String, Long>> startAndAgeCount = new HashMap<>();
        for (Object[] result : totalResults) {
            String ageGroup = (String) result[0];
            if (!"ADMIN".equals(ageGroup)) {
                Long totalCount = (Long) result[1];
                startAndAgeCount.put(ageGroup, new HashMap<>());
                startAndAgeCount.get(ageGroup).put("totalCount", totalCount);
            }
        }
        
        for (Object[] result : startTrueResults) {
            String ageGroup = (String) result[0];
            if (!"ADMIN".equals(ageGroup)) {
                Long startTrueCount = (Long) result[1];
                if (!startAndAgeCount.containsKey(ageGroup)) {
                    startAndAgeCount.put(ageGroup, new HashMap<>());
                }
                startAndAgeCount.get(ageGroup).put("startTrueCount", startTrueCount);
            }
        }
        
        return startAndAgeCount;
    }
    // 이주명(0405) 위에 참고해서 함 해보기.
    public List<UserMemberDTO> getMemberList() {
        // UserRepository에서 User 리스트를 가져옴
        List<User> users = userRepository.findAll();

        // User를 UserMemberDTO로 매핑하여 반환
        List<UserMemberDTO> memberList = users.stream()
                .filter(user -> !"ADMIN".equals(user.getPhone()))
                .map(user -> new UserMemberDTO(
                        user.getPhone(),
                        user.getName(),
                        user.getNation(),
                        user.getAge(),
                        user.getGender(),
                        user.isStart(),
                        user.getJoin_dt()
                ))
                .collect(Collectors.toList());
                log.info(memberList);

            return memberList;
    }
    // 이주명(0405) 여기까지
}
