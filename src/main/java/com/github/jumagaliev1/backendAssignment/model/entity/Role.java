package com.github.jumagaliev1.backendAssignment.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;

@Entity
@Table(name = Role.TABLE_NAME)
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Role {
    public static final String TABLE_NAME = "roles";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";

    public static final int NAME_COLUMN_LENGTH = 32;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_COLUMN)
    private Long id;

    @Column(name = NAME_COLUMN, length = NAME_COLUMN_LENGTH)
    private String name;
}
