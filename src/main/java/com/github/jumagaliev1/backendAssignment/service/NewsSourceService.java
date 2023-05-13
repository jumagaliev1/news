package com.github.jumagaliev1.backendAssignment.service;

import com.github.jumagaliev1.backendAssignment.model.entity.NewsSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface NewsSourceService {
    NewsSource create(NewsSource source);

    NewsSource update(Long id, NewsSource source);

    void delete(Long id);

    NewsSource getById(Long id);

    Page<NewsSource> getAll(Pageable pageable);
}
