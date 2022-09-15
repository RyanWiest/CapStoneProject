package com.kenzie.appserver.controller;

import com.kenzie.appserver.service.CategoryService;
import com.kenzie.appserver.controller.model.CategoryResponse;
import com.kenzie.appserver.service.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    CategoryController(CategoryService categoryService){

        this.categoryService = categoryService;
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<CategoryResponse> getQuestionById(@PathVariable("questionId") String questionId) {
        Category category =
                categoryService.getQuestionById(questionId); //
        // needs implementation
        if (category == null) {
            return ResponseEntity.noContent().build();
        }

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setQuestionId(questionId);
        categoryResponse.setQuestions(category.getQuestions());
        categoryResponse.setAnswers(category.getAnswers());
        categoryResponse.setDifficultyOfQuestion(category.getDifficultyOfAQuestion());

        return ResponseEntity.ok(categoryResponse);
    }

}
