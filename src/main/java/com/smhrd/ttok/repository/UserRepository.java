package com.smhrd.ttok.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smhrd.ttok.domain.User;

// 손승아, User 엔티티 처리하는 repository 생성, 20240316
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByPhone(String phone);

    @Query("SELECT u.age, COUNT(u) FROM User u GROUP BY u.age ORDER BY COUNT(u) DESC")
    List<Object[]> countByAgeGroup();

    @Query("SELECT u.age, COUNT(u) FROM User u WHERE u.start = true GROUP BY u.age ORDER BY COUNT(u) DESC")
    List<Object[]> countStartTrueByAgeGroup();
    
}
