package com.github.jumagaliev1.backendAssignment.model.response;

import com.github.jumagaliev1.backendAssignment.model.entity.News;
import com.github.jumagaliev1.backendAssignment.model.entity.NewsSource;
import com.github.jumagaliev1.backendAssignment.model.entity.NewsTopic;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
public class NewsResponse {
    private Long id;
    private String title;

    private String content;

    private NewsSource source;

    private Set<NewsTopic> topics;

    private LocalDateTime createdAt;

    public static NewsResponse fromDomain(News news) {
        return new NewsResponse(
                news.getId(),
                news.getTitle(),
                news.getContent(),
                news.getSource(),
                news.getTopics(),
                news.getCreatedAt());
    }
}
