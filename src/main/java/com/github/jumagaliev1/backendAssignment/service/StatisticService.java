package com.github.jumagaliev1.backendAssignment.service;

import com.github.jumagaliev1.backendAssignment.model.entity.CountNews;

import java.util.List;

public interface StatisticService {
    void generateStatistics();

    List<CountNews> getCountNews();
}
