package com.blez.newsapi_springboot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PreferenceModel {
    private String topic;
    private String country;
    private String language;
    private String domain;
    private Category category;
    private String userId;

}
