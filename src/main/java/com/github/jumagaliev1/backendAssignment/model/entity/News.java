package com.github.jumagaliev1.backendAssignment.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(
        name = News.TABLE_NAME,
        schema = "public"
)
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

    @Id
    @Column(name = ID_COLUMN)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = TITLE_COLUMN, nullable = false)
    private String title;

    @Column(name = CONTENT_COLUMN, nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = SOURCE_ID_COLUMN)
    private NewsSource source;

    @ManyToMany
    @JoinTable(name = NEWS_TOPICS_COLUMN,
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id"))
    private Set<NewsTopic> topics = new HashSet<>();


}
