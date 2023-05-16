package com.github.jumagaliev1.backendAssignment.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = Role.TABLE_NAME)
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Role {
    public static final int NAME_COLUMN_LENGTH = 32;

    public static final String TABLE_NAME = "roles";

    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";

    @Id
    @Column(name = ID_COLUMN)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = NAME_COLUMN, length = NAME_COLUMN_LENGTH)
    private String name;
}
