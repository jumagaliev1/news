package com.github.jumagaliev1.backendAssignment.model.entity;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = News.TABLE_NAME)
@Getter
@Setter
@ToString
@NoArgsConstructor
public class News {
    public static final String TABLE_NAME = "news";
    public static final String ID_COLUMN = "id";
    public static final String TITLE_COLUMN = "title";
    public static final String CONTENT_COLUMN = "content";
    public static final String SOURCE_COLUMN = "source";
    public static final String SOURCE_ID_COLUMN = "source_id";
    public static final String NEWS_TOPICS_COLUMN = "news_topics_news";
    public static final String NEWS_ID_COLUMN = "news_id";
    public static final String TOPIC_ID_COLUMN = "topic_id";
    public static final String AUTHOR_COLUMN = "author_id";
    public static final String CREATED_AT_COLUMN = "created_at";
    public static final String UPDATED_AT_COLUMN = "updated_at";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_COLUMN)
    private Long id;

    @Column(name = TITLE_COLUMN, nullable = false)
    private String title;

    @Column(name = CONTENT_COLUMN, nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = SOURCE_ID_COLUMN)
    private NewsSource source;

    @ManyToMany
    @JoinTable(
            name = NEWS_TOPICS_COLUMN,
            joinColumns = @JoinColumn(name = NEWS_ID_COLUMN),
            inverseJoinColumns = @JoinColumn(name = TOPIC_ID_COLUMN)
    )
    private Set<NewsTopic> topics = new HashSet<>();

    @Column(name = CREATED_AT_COLUMN)
    private LocalDateTime createdAt;

    @Column(name = UPDATED_AT_COLUMN)
    private LocalDateTime updatedAt;

    @Column(name = AUTHOR_COLUMN)
    private Long Author;
}
