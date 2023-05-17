package com.github.jumagaliev1.backendAssignment.model.entity;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = NewsSource.TABLE_NAME)
@Getter
@Setter
@ToString
@NoArgsConstructor
public class NewsSource {
    public static final String TABLE_NAME = "news_source";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_COLUMN)
    private Long id;

    @Column(name = NAME_COLUMN, nullable = false)
    private String name;
}

