package com.blez.newsapi_springboot.repository;

import com.blez.newsapi_springboot.model.Preferences;
import com.blez.newsapi_springboot.model.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PreferenceRepository extends JpaRepository<Preferences, Long> {

        Preferences findByUserTable(UserTable userTable);
}
