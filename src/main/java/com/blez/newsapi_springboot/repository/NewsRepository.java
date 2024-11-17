package com.blez.newsapi_springboot.repository;

import com.blez.newsapi_springboot.model.NewsHistory;
import com.blez.newsapi_springboot.model.NewsModel;
import com.blez.newsapi_springboot.model.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<NewsHistory,Long> {

    NewsHistory findByUserTable(UserTable userTable);
}
