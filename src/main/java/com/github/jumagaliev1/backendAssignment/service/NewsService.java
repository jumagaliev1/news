package com.github.jumagaliev1.backendAssignment.service;

import com.github.jumagaliev1.backendAssignment.model.entity.News;
import com.github.jumagaliev1.backendAssignment.model.request.NewsRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface NewsService {
    News create(NewsRequest request);

    News update(Long id, News news);

    void delete(Long id);

    News getById(Long id);

    Page<News> getAll(Pageable pageable);

    Page<News> getBySourceId(Long sourceId, Pageable pageable);

    Page<News> getByTopicId(Long topicId, Pageable pageable);

}
