package com.github.jumagaliev1.backendAssignment.service;


import com.github.jumagaliev1.backendAssignment.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User getById(Long id);

    User findByUsername(String name);

}
