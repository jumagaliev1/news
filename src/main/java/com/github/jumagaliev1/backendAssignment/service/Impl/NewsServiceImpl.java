package com.github.jumagaliev1.backendAssignment.service.Impl;

import com.github.jumagaliev1.backendAssignment.exception.ResourceNotFoundException;
import com.github.jumagaliev1.backendAssignment.model.entity.News;
import com.github.jumagaliev1.backendAssignment.model.entity.NewsSource;
import com.github.jumagaliev1.backendAssignment.model.entity.NewsTopic;
import com.github.jumagaliev1.backendAssignment.repository.NewsRepository;
import com.github.jumagaliev1.backendAssignment.service.NewsService;
import com.github.jumagaliev1.backendAssignment.service.NewsSourceService;
import com.github.jumagaliev1.backendAssignment.service.NewsTopicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class NewsServiceImpl implements NewsService {

    private final NewsRepository repository;
    private final NewsSourceService sourceService;
    private final NewsTopicService topicService;

    @Override
    public News create(News news) {
        NewsSource source = sourceService.getById(news.getSource().getId());
        news.setSource(source);

        Set<NewsTopic> topics = new HashSet<>();
        for (NewsTopic topic : news.getTopics()) {
            topics.add(topicService.getById(topic.getId()));
        }
        news.setTopics(topics);

        return repository.save(news);
    }

    @Override
    public News update(Long id, News news) {
        News existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News", "id", id));

        existing.setTitle(news.getTitle());
        existing.setContent(news.getContent());

        NewsSource source = sourceService.getById(news.getSource().getId());
        existing.setSource(source);

        Set<NewsTopic> topics = new HashSet<>();
        for (NewsTopic topic : news.getTopics()) {
            topics.add(topicService.getById(topic.getId()));
        }
        existing.setTopics(topics);

        return repository.save(existing);    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public News getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News", "id", id));
    }

    @Override
    public Page<News> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<News> getBySourceId(Long sourceId, Pageable pageable) {
        return repository.findBySourceId(sourceId, pageable);
    }

    @Override
    public Page<News> getByTopicId(Long topicId, Pageable pageable) {
        return repository.findByTopicsId(topicId, pageable);
    }
}
