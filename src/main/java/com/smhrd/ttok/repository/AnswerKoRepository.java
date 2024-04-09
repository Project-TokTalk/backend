package com.smhrd.ttok.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.smhrd.ttok.domain.Answer_Ko;

public interface AnswerKoRepository extends JpaRepository<Answer_Ko,Integer> {
    List<Answer_Ko> findAll();
}
