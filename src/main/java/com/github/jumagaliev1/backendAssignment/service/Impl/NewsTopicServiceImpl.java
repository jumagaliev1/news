package com.github.jumagaliev1.backendAssignment.service.Impl;

import com.github.jumagaliev1.backendAssignment.exception.ResourceNotFoundException;
import com.github.jumagaliev1.backendAssignment.model.entity.NewsTopic;
import com.github.jumagaliev1.backendAssignment.repository.NewsTopicRepository;
import com.github.jumagaliev1.backendAssignment.service.NewsTopicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class NewsTopicServiceImpl implements NewsTopicService {
    private final NewsTopicRepository repository;
    @Override
    public NewsTopic create(NewsTopic topic) {
        return repository.save(topic);
    }

    @Override
    public NewsTopic update(Long id, NewsTopic topic) {
        NewsTopic existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News topic", "id", id));
        existing.setName(topic.getName());
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public NewsTopic getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News topic", "id", id));
    }

    @Override
    public Page<NewsTopic> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
