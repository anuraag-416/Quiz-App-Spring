package com.anuraag.quizapp.dao;

import com.anuraag.quizapp.model.QuizExam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<QuizExam,Integer> {
}
