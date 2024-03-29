package com.github.jumagaliev1.backendAssignment.controller;

import com.github.jumagaliev1.backendAssignment.model.entity.CountNews;
import com.github.jumagaliev1.backendAssignment.model.entity.News;
import com.github.jumagaliev1.backendAssignment.model.request.NewsRequest;
import com.github.jumagaliev1.backendAssignment.model.response.NewsResponse;
import com.github.jumagaliev1.backendAssignment.service.NewsService;
import com.github.jumagaliev1.backendAssignment.service.StatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
@Log4j2
public class NewsController {

    private final NewsService service;
    private final StatisticService statisticService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public NewsResponse create(@RequestBody @Valid NewsRequest request) {
        return NewsResponse.fromDomain(service.create(request));
    }

    @PutMapping("/{id}")
    public NewsResponse update(@PathVariable Long id, @RequestBody @Valid News news) {
        return NewsResponse.fromDomain(service.update(id, news));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public NewsResponse getById(@PathVariable Long id) {
        return NewsResponse.fromDomain(service.getById(id));
    }

    @GetMapping
    public Page<News> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/stat")
    public List<CountNews> getCountNews() {
        return statisticService.getCountNews();
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
