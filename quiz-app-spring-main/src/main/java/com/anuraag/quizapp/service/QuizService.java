package com.anuraag.quizapp.service;

import com.anuraag.quizapp.model.Questions;
import com.anuraag.quizapp.model.Answers;
import com.anuraag.quizapp.dao.QuestionDao;
import com.anuraag.quizapp.dao.QuizDao;
import com.anuraag.quizapp.model.QuestionWrapper;
import com.anuraag.quizapp.model.QuizExam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Questions> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        QuizExam quizExam = new QuizExam();
        quizExam.setTitle(title);
        quizExam.setQuestions(questions);
        quizDao.save(quizExam);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<QuizExam> quiz = quizDao.findById(id);
        List<Questions> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Questions q : questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOptionOne(), q.getOptionTwo(), q.getOptionThree(), q.getOptionFour());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Answers> respons) {
        QuizExam quizExam = quizDao.findById(id).get();
        List<Questions> questions = quizExam.getQuestions();
        int right = 0;
        int i = 0;
        for(Answers answers : respons){
            if(answers.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;

            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
