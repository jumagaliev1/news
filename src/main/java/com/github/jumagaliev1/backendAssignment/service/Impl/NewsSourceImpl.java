package com.github.jumagaliev1.backendAssignment.service.Impl;

import com.github.jumagaliev1.backendAssignment.exception.ResourceNotFoundException;
import com.github.jumagaliev1.backendAssignment.model.entity.NewsSource;
import com.github.jumagaliev1.backendAssignment.repository.NewsSourceRepository;
import com.github.jumagaliev1.backendAssignment.service.NewsSourceService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class NewsSourceImpl implements NewsSourceService {

    private final NewsSourceRepository repository;

    @Override
    public NewsSource create(NewsSource source) {
        return repository.save(source);
    }

    @Override
    public NewsSource update(Long id, NewsSource source) {
        NewsSource existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News source", "id", id));
        existing.setName(source.getName());
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public NewsSource getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News source", "id", id));
    }

    @Override
    public Page<NewsSource> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
