package com.blez.newsapi_springboot.repository;


import com.blez.newsapi_springboot.model.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@Repository
public interface H2Repository extends JpaRepository<UserTable, Long> {

    UserTable findByUsername(String username);



}
