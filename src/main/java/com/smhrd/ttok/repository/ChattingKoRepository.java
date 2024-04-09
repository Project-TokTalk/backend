package com.smhrd.ttok.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smhrd.ttok.domain.Chatting_Ko;

public interface ChattingKoRepository extends JpaRepository<Chatting_Ko,Integer> {
    @Query("SELECT ck FROM Chatting_Ko ck")
    List<Chatting_Ko> findAllChattingKo();

}
