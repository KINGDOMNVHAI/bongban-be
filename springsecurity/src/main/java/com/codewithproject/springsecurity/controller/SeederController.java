package com.codewithproject.springsecurity.controller;

import com.codewithproject.springsecurity.entities.CategoryTest;
import com.codewithproject.springsecurity.entities.Language;
import com.codewithproject.springsecurity.entities.Question;
import com.codewithproject.springsecurity.entities.Test;
import com.codewithproject.springsecurity.entities.User;
import com.codewithproject.springsecurity.services.impl.CategoryTestServiceImpl;
import com.codewithproject.springsecurity.services.impl.LanguageServiceImpl;
import com.codewithproject.springsecurity.services.impl.QuestionServiceImpl;
import com.codewithproject.springsecurity.services.impl.TestServiceImpl;
import com.codewithproject.springsecurity.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/seeder")
@RequiredArgsConstructor
public class SeederController {

    @Autowired
    private CategoryTestServiceImpl categorytestServiceImpl;

    @Autowired
    private LanguageServiceImpl languageServiceImpl;

    @Autowired
    private QuestionServiceImpl questionServiceImpl;

    @Autowired
    private TestServiceImpl testServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/category-test")
    public List<CategoryTest> migrateCategoryTest() {
        return categorytestServiceImpl.seederCategoryTest();
    }

    @GetMapping("/languages")
    public List<Language> migrateLanguage(){
        return languageServiceImpl.seederLanguages();
    }

    @GetMapping("/questions")
    public List<Question> migrateQuestion() {
        return questionServiceImpl.seederQuestions();
    }

    @GetMapping("/tests")
    public List<Test> migrateTest() {
        return testServiceImpl.seederTests();
    }

    @GetMapping("/user")
    public List<User> migrateUser() {
        return userServiceImpl.seederUser();
    }
}
