package com.github.jumagaliev1.backendAssignment.repository;

import com.github.jumagaliev1.backendAssignment.model.entity.CountNews;
import com.github.jumagaliev1.backendAssignment.model.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    Page<News> findBySourceId(Long sourceId, Pageable pageable);
    Page<News> findByTopicsId(Long topicId, Pageable pageable);

    @Query("SELECT news.source.name, COUNT(news) as newsCount FROM News news GROUP BY news.source.name")
    List<Object[]> countNewsBySource();

}
