package com.github.jumagaliev1.backendAssignment.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = NewsSource.TABLE_NAME,
        schema = "public"
)
@Getter
@Setter
@ToString
@NoArgsConstructor
public class NewsSource {
    public static final String TABLE_NAME = "news_source";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";

    @Id
    @Column(name = ID_COLUMN)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = NAME_COLUMN, nullable = false)
    private String name;
}

