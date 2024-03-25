package com.smhrd.ttok.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smhrd.ttok.domain.User;

// 손승아, User 엔티티 처리하는 repository 생성, 20240316
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByPhone(String phone);
    
}
