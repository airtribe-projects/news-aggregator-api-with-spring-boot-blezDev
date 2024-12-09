package com.blez.newsapi_springboot.service;

import com.blez.newsapi_springboot.model.*;
import com.blez.newsapi_springboot.repository.H2Repository;
import com.blez.newsapi_springboot.repository.NewsRepository;
import com.blez.newsapi_springboot.repository.PreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import static com.blez.newsapi_springboot.model.Utils.EVERYTHING;

@Service
public class NewsService {

    @Autowired
    private H2Repository h2Repository;

    @Autowired
    private PreferenceRepository preferenceRepository;

    @Autowired
    private NewsRepository newsRepository;


    public NewsModel getNews(String userId) {
        try {
            Preferences prefs = preferenceRepository.findByUserTable(h2Repository.findByUsername(userId));
            NewsModel news = WebClient.create().get()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("https")
                            .host("newsapi.org")
                            .path(EVERYTHING)
                            .queryParam("apiKey", Utils.NewsAPI)
                            .queryParam("q", prefs.getTopic())
                            .queryParam("sortBy", prefs.getCategory().name())
                            .build())
                    .retrieve()
                    .bodyToMono(NewsModel.class)
                    .block();
            saveNewsHistory(news, userId);
            return news;
        } catch (Exception e) {
            // Handling exceptions and rethrowing them
            throw new RuntimeException("An unexpected error occurred while fetching news.", e);
        }
    }

    public NewsModel getSearchNews(String search,String userId) {
        try {
            Preferences prefs = preferenceRepository.findByUserTable(h2Repository.findByUsername(userId));
            return WebClient.create().get()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("https")
                            .host("newsapi.org")
                            .path(EVERYTHING)
                            .queryParam("apiKey", search)
                            .queryParam("sortBy", prefs.getCategory().name())
                            .build())
                    .retrieve()
                    .bodyToMono(NewsModel.class)
                    .block();
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while fetching news.", e);
        }
    }


    public Preferences getPreference(String userId) {
        try {
            return preferenceRepository.findByUserTable(h2Repository.findByUsername(userId));
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while fetching preferences.", e);
        }
    }

    public Preferences updatepreference(PreferenceModel preference) {
        try {
            Preferences prefs = Preferences
                    .builder()
                    .topic(preference.getTopic())
                    .category(preference.getCategory())
                    .userTable(h2Repository.findByUsername(preference.getUserId()))
                    .build();
            return preferenceRepository.save(prefs);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while setting preferences.", e);
        }
    }

    public NewsHistory getNewsHistory(String userId) {
        try {
            return newsRepository.findByUserTable(h2Repository.findByUsername(userId));
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while fetching news history.", e);
        }
    }


    public void saveNewsHistory(NewsModel newsModel, String userId) {
        try {
            UserTable userTable = h2Repository.findByUsername(userId);
            NewsHistory newsHistory = NewsHistory.builder()
                    .totalResults(newsModel.getTotalResults())
                    .status(newsModel.getStatus())
                    .userTable(userTable)
                    .articlesList(newsModel.getArticles())
                    .build();
            newsRepository.save(newsHistory);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while saving news history.", e);
        }
    }


}

