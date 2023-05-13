package com.github.jumagaliev1.backendAssignment.controller;

import com.github.jumagaliev1.backendAssignment.model.entity.News;
import com.github.jumagaliev1.backendAssignment.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService service;
    @PostMapping
    public News create(@RequestBody @Valid News news) {
        return service.create(news);
    }

    @PutMapping("/{id}")
    public News update(@PathVariable Long id, @RequestBody @Valid News news) {
        return service.update(id, news);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public News getById(@PathVariable Long id) {
        return service.getById(id);
    }


    @GetMapping
    public Page<News> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/by-source/{sourceId}")
    public Page<News> getBySourceId(@PathVariable Long sourceId,
                                    @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.getBySourceId(sourceId, pageable);
    }

    @GetMapping("/by-topic/{topicId}")
    public Page<News> getByTopicId(@PathVariable Long topicId,
                                   @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.getByTopicId(topicId, pageable);
    }


}
