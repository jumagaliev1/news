package com.github.jumagaliev1.backendAssignment.repository;

import com.github.jumagaliev1.backendAssignment.model.entity.NewsSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsSourceRepository extends JpaRepository<NewsSource, Long> {

}