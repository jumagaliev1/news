package com.github.jumagaliev1.backendAssignment.controller;

import com.github.jumagaliev1.backendAssignment.model.entity.NewsTopic;
import com.github.jumagaliev1.backendAssignment.service.NewsTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/news-topics")
@RequiredArgsConstructor
public class NewsTopicController {
    private final NewsTopicService service;

    @PostMapping
    public NewsTopic create(@RequestBody @Valid NewsTopic topic) {
        return service.create(topic);
    }

    @PutMapping("/{id}")
    public NewsTopic update(@PathVariable Long id, @RequestBody @Valid NewsTopic topic) {
        return service.update(id, topic);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public NewsTopic getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public Page<NewsTopic> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.getAll(pageable);
    }
}
