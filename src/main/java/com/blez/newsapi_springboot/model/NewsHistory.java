package com.blez.newsapi_springboot.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@JsonSerialize
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class NewsHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private long totalResults;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Article> articlesList = new ArrayList<>();
    private String status;

    @ManyToOne
    private UserTable userTable;

}
