package com.github.jumagaliev1.backendAssignment.service.Impl;

import com.github.jumagaliev1.backendAssignment.model.entity.CountNews;
import com.github.jumagaliev1.backendAssignment.model.entity.News;
import com.github.jumagaliev1.backendAssignment.repository.NewsRepository;
import com.github.jumagaliev1.backendAssignment.repository.NewsSourceRepository;
import com.github.jumagaliev1.backendAssignment.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final NewsRepository newsRepository;

    @Scheduled(cron = "0 0 0 * * *")
    @Override
    public void generateStatistics() {
        List<Object[]> results = newsRepository.countNewsBySource();

        String filePath = "./statistics.txt";
        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            for (Object[] result : results) {
                String newsSource = Objects.toString(result[0], "");
                Long newsCount = (Long) result[1];
                writer.println(newsSource + "," + newsCount);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CountNews> getCountNews() {
        List<CountNews> list = new ArrayList<>();
        List<Object[]> results = newsRepository.countNewsBySource();
        for (Object[] result : results) {
            String newsSource = Objects.toString(result[0], "");
            Long newsCount = (Long) result[1];
            CountNews cn = new CountNews();
            cn.setNewsSource(newsSource);
            cn.setNewsCount(newsCount);
            list.add(cn);
        }
        return list;
    }
}
