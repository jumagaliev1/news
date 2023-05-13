package com.github.jumagaliev1.backendAssignment.service;

import com.github.jumagaliev1.backendAssignment.model.entity.NewsTopic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface NewsTopicService {

    NewsTopic create(NewsTopic topic);

    NewsTopic update(Long id, NewsTopic topic);

    void delete(Long id);

    NewsTopic getById(Long id);

    Page<NewsTopic> getAll(Pageable pageable);
}
