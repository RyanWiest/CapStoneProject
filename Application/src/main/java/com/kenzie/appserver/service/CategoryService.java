package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.CategoryRepository;
import com.kenzie.appserver.repositories.model.CategoryRecord;
import com.kenzie.appserver.service.model.Category;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Scanner;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;


    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryRecord getQuestionById(String questionId) {
        // getting data from the local repository
        if (categoryRepository
                .findById(questionId).isPresent()) {
            CategoryRecord questionRecord = categoryRepository
                    .findById(questionId).get();
            return questionRecord;
        }

        return null;

    }
    // user answers question and gets feedback on answer.
    // if the user answers correctly move difficulty up and give user 1 point.
    // if user answers incorrectly show user correct answer then move the difficulty down a level.
    public void getUserAnswer(Category question) {

        // I will need to create 5 different ways the user will be given if they are wrong or right
        // I will need a scanner object in order to see the user answer
        // I will need a way for the question to match up with the answer. Maybe method isn't created yet?
        Scanner myScanner = new Scanner(System.in);
        String userName = myScanner.next();
        String answer = question.getAnswers();

        if (userName.equals(answer)) {
            helperMethodForCorrectAnswer();
            // if the user gets the correct answer we want to make it more difficult for the user next time
            if(question.getDifficultyOfAQuestion().equals("easy")){
                question.setDifficultyOfAQuestion("hard");
            } else if(question.getDifficultyOfAQuestion().equals("hard")){
                // if use is already on hard do nothing
            }
        } else {
            helperMethodForIncorrectAnswer();
            // shows user correct answer
            System.out.println("This is the correct answer " + answer);
            // if they are already on an easier question, give them another easy question.
            if (question.getDifficultyOfAQuestion().equals("easy")) {
                // do nothing they will need an easy question again
            } else if (question.getDifficultyOfAQuestion().equals("hard")) {
                // if player is on a hard question, make the next question an easier question.
                question.setDifficultyOfAQuestion("easy"); // seems wrong, will revisit
                // may have to set whole thing
            }
        }
    }

    // helper method to get correct answer.
    private String helperMethodForCorrectAnswer() {

        String[] arrayRightAnswers = new String[]{"You have hit a nail on the head.", "Yes, that’s very correct.", "You are quite right.",
                "Great job! You got it", "That’s spot on."};

        return arrayRightAnswers[(int) (Math.random() * arrayRightAnswers.length)];
    }

    // helper method to get incorrect answer
    private String helperMethodForIncorrectAnswer() {
        String[] arrayWrongAnswers = new String[]{"You thought wrong!", "Sorry that is the incorrect answer", "oooo sorry, so close",
                "Would you mind to think twice on what you’re saying", "Where did you hear that?"};

        return arrayWrongAnswers[(int) (Math.random() * arrayWrongAnswers.length)];
    }

}
