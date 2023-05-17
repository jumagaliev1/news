package com.github.jumagaliev1.backendAssignment.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = User.TABLE_NAME,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = User.USERNAME_COLUMN)
        })
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
    public static final String TABLE_NAME = "users";
    public static final String ID_COLUMN = "id";
    public static final String USERNAME_COLUMN = "username";
    public static final String PASSWORD_COLUMN = "password";
    public static final String EMAIL_COLUMN = "email";
    public static final String CREATED_AT_COLUMN = "created_at";
    public static final String UPDATED_AT_COLUMN = "updated_at";

    public static final int EMAIL_COLUMN_LENGTH = 64;
    public static final int PASSWORD_COLUMN_LENGTH = 64;
    public static final int USERNAME_COLUMN_LENGTH = 32;

    @Id
    @Column(name = ID_COLUMN)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = USERNAME_COLUMN, nullable = false, length = USERNAME_COLUMN_LENGTH)
    private String username;

    @Column(name = PASSWORD_COLUMN, nullable = false, length = PASSWORD_COLUMN_LENGTH)
    private String password;

    @Column(name = EMAIL_COLUMN, nullable = false, length = EMAIL_COLUMN_LENGTH)
    private String email;

    @Column(name = CREATED_AT_COLUMN)
    private LocalDateTime createdAt;

    @Column(name = UPDATED_AT_COLUMN)
    private LocalDateTime updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles = new HashSet<>();


}
