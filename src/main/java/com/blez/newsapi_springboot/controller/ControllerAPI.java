package com.blez.newsapi_springboot.controller;

import com.blez.newsapi_springboot.model.*;
import com.blez.newsapi_springboot.service.AuthService;
import com.blez.newsapi_springboot.service.NewsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ControllerAPI {

    @Autowired
    private AuthService authService;

    @Autowired
    private NewsService newsService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginModel loginModel) {
        String login = authService.login(loginModel);
        return ResponseEntity.status(200).body(login);
    }


    @PostMapping("/register")
    public ResponseEntity<String> register( @RequestBody @Valid RegisterModel registerModel) {
        try {
            boolean register = authService.register(registerModel);
            if (register){
                return ResponseEntity.status(201).body("User Registered Successfully");
            }
            return ResponseEntity.status(400).body("User Registration Failed");
        }catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/preferences")
    public ResponseEntity<Preferences> getPreference(@Valid @PathVariable(name = "userId") String userId) {
        try {
            Preferences preference = newsService.getPreference(userId);
            if (preference != null){
                return ResponseEntity.status(200).body(preference);
            }
            throw new RuntimeException("An unexpected error occurred while fetching news.");
        }catch (Exception e){
            throw new RuntimeException("An unexpected error occurred while fetching news.", e);
        }
    }

    @PutMapping("/preferences")
    public ResponseEntity<String> setPreference(@Valid @RequestBody PreferenceModel preference) {
        try {
            Preferences prefs = newsService.updatepreference(preference);
            if (prefs != null) {
                return ResponseEntity.status(200).body("Preferences Updated Successfully");
            }
            return ResponseEntity.status(400).body("Preferences Update Failed");
        }catch (Exception e){
            throw new RuntimeException("An unexpected error occurred while fetching news.", e);
        }
    }

    @GetMapping("/news")
    public ResponseEntity<NewsModel> news(@Valid @PathVariable(name = "userId") String userId) {

        try {
            NewsModel news = newsService.getNews(userId);
            if (news != null){
                return ResponseEntity.status(200).body(news);
            }
            return ResponseEntity.status(400).body(null);
        }catch (Exception e){
            throw new RuntimeException("An unexpected error occurred while fetching news.", e);
        }
    }

    @GetMapping("/news/search")
    public ResponseEntity<NewsModel> news(@Valid @PathVariable(name = "keyword") String keyword, @Valid @PathVariable(name = "userId") String userId) {

        try {
            NewsModel news = newsService.getSearchNews(keyword, userId);
            if (news != null){
                return ResponseEntity.status(200).body(news);
            }
            return ResponseEntity.status(400).body(null);
        }catch (Exception e){
            throw new RuntimeException("An unexpected error occurred while fetching news.", e);
        }
    }


    @GetMapping("/news/read")
    public ResponseEntity<NewsHistory> getNewsRead( @Valid @PathVariable(name = "userId") String userId) {

        try {
            NewsHistory newsHistory = newsService.getNewsHistory(userId);
            if (newsHistory != null){
                return ResponseEntity.status(200).body(newsHistory);
            }
            return ResponseEntity.status(400).body(null);
        }catch (Exception e){
            throw new RuntimeException("An unexpected error occurred while fetching news.", e);
        }
    }


}
