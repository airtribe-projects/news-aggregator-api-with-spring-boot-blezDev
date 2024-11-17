// NewsModel.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.blez.newsapi_springboot.model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import java.time.OffsetDateTime;

@Data
@JsonSerialize
@NoArgsConstructor
@AllArgsConstructor
public class NewsModel {
    private long totalResults;
    private List<Article> articles;
    private String status;

    public long getTotalResults() { return totalResults; }
    public void setTotalResults(long value) { this.totalResults = value; }

    public List<Article> getArticles() { return articles; }
    public void setArticles(List<Article> value) { this.articles = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }

}

// Article.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation


@Data
@JsonSerialize
@Entity
class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private OffsetDateTime publishedAt;
    private String author;
    private String urlToImage;
    private String description;
    @ManyToOne
    private Source source;
    private String title;
    private String url;
    private String content;

    public OffsetDateTime getPublishedAt() { return publishedAt; }
    public void setPublishedAt(OffsetDateTime value) { this.publishedAt = value; }

    public String getAuthor() { return author; }
    public void setAuthor(String value) { this.author = value; }

    public String geturlToImage() { return urlToImage; }
    public void seturlToImage(String value) { this.urlToImage = value; }

    public String getDescription() { return description; }
    public void setDescription(String value) { this.description = value; }

    public Source getSource() { return source; }
    public void setSource(Source value) { this.source = value; }

    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }

    public String geturl() { return url; }
    public void seturl(String value) { this.url = value; }

    public String getContent() { return content; }
    public void setContent(String value) { this.content = value; }
}

// Source.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

@Data
@Entity

class Source {
    private String name;
    @Id
    private String id;

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getid() { return id; }
    public void setid(String value) { this.id = value; }
}
