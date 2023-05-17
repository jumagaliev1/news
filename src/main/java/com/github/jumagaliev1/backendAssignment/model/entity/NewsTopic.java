package com.github.jumagaliev1.backendAssignment.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = NewsTopic.TABLE_NAME)
@Getter
@Setter
@ToString
@NoArgsConstructor
public class NewsTopic {
    public static final String TABLE_NAME = "news_topics";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";

    public static final int NAME_COLUMN_LENGTH = 32;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_COLUMN)
    private Long id;

    @Column(name = NAME_COLUMN, nullable = false, length = NAME_COLUMN_LENGTH)
    private String name;
}
