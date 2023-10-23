package com.anuraag.quizapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String questionTitle;
    private String optionOne;
    private String optionTwo;
    private String optionThree;
    private String optionFour;
    private String rightAnswer;
    private String difficulty;
    private String category;


}
