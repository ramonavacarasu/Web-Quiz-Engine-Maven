package com.example.demo.service;

import com.example.demo.model.Answer;
import com.example.demo.model.FeedBack;
import com.example.demo.model.Quiz;
import com.example.demo.repositories.QuizRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Quiz> getAll() {
        return quizRepository.findAll();
    }

    public Quiz getById(String id) {
        Quiz quiz = quizRepository.findById(Integer.parseInt(id)).orElse(null);

        if (quiz == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return quiz;
    }

    public FeedBack solve(Answer answer, String id) {
        Quiz quiz = getById(id);
        if (answer.getAnswer().equals(quiz.getAnswer())) {
            return new FeedBack(true);
        }

        return new FeedBack(false);
    }

}
