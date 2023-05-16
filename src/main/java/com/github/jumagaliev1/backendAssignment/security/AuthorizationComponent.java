package com.github.jumagaliev1.backendAssignment.security;


import com.github.jumagaliev1.backendAssignment.model.entity.User;
import com.github.jumagaliev1.backendAssignment.service.Impl.UserServiceImpl;
import com.github.jumagaliev1.backendAssignment.service.NewsService;
import com.github.jumagaliev1.backendAssignment.model.entity.News;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component("A")
@RequiredArgsConstructor
public class AuthorizationComponent {

    private final UserServiceImpl userService;
    private final NewsService newsService;

    public Boolean isAuthor(@NonNull final Principal principal, @NonNull final Long id) {
        User user = userService.findByUsername(principal.getName());
        News news = newsService.getById(id);

        return user.getId().equals(news.getAuthor());
    }
}
