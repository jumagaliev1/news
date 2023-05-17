package com.github.jumagaliev1.backendAssignment.controller;

import com.github.jumagaliev1.backendAssignment.model.entity.NewsSource;
import com.github.jumagaliev1.backendAssignment.service.NewsSourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/news-sources")
@RequiredArgsConstructor
public class NewsSourceController {

    private final NewsSourceService service;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public NewsSource create(@RequestBody @Valid NewsSource source) {
        return service.create(source);
    }

    @PutMapping("/{id}")
    public NewsSource update(@PathVariable Long id, @RequestBody @Valid NewsSource source) {
        return service.update(id, source);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public NewsSource getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public Page<NewsSource> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.getAll(pageable);
    }
}
