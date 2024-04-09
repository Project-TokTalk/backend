package com.smhrd.ttok.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smhrd.ttok.domain.Chatting_En;

public interface ChattingEnRepository extends JpaRepository<Chatting_En, Integer> {
    @Query("SELECT ce FROM Chatting_En ce")
    List<Chatting_En> findAllChattingEn();
} 