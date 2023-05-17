package com.github.jumagaliev1.backendAssignment.service;

import com.github.jumagaliev1.backendAssignment.exception.ResourceNotFoundException;
import com.github.jumagaliev1.backendAssignment.model.entity.News;
import com.github.jumagaliev1.backendAssignment.model.entity.NewsSource;
import com.github.jumagaliev1.backendAssignment.model.entity.NewsTopic;
import com.github.jumagaliev1.backendAssignment.model.request.NewsRequest;
import com.github.jumagaliev1.backendAssignment.repository.NewsRepository;
import com.github.jumagaliev1.backendAssignment.service.Impl.NewsServiceImpl;
import com.github.jumagaliev1.backendAssignment.service.Impl.NewsSourceImpl;
import com.github.jumagaliev1.backendAssignment.service.Impl.NewsTopicServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RequiredArgsConstructor
public class NewsServiceTest {

    @Mock
    private NewsRepository newsRepository;

    @Mock
    private NewsSourceImpl newsSourceService;

    @Mock
    private NewsTopicServiceImpl newsTopicService;

    @InjectMocks
    private NewsServiceImpl newsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        newsService = new NewsServiceImpl(newsRepository, newsSourceService, newsTopicService);
    }

    @Test
    public void testCreateNews() {
        NewsRequest request = new NewsRequest();
        request.setSource_id(1L);
        request.setTopic_id(Collections.singletonList(1L));

        News news = new News();
        news.setTitle("Test News");
        news.setContent("This is a test news");

        when(newsSourceService.getById(anyLong())).thenReturn(new NewsSource());
        when(newsTopicService.getById(anyLong())).thenReturn(new NewsTopic());
        when(newsRepository.save(any(News.class))).thenReturn(news);

        News createdNews = newsService.create(request);

        assertNotNull(createdNews);

        verify(newsSourceService).getById(eq(1L));
        verify(newsTopicService).getById(eq(1L));
        verify(newsRepository).save(any(News.class));
    }

    @Test
    public void testUpdateNews() {
        Long newsId = 1L;
        News existingNews = new News();
        existingNews.setId(newsId);
        existingNews.setTitle("Existing News");
        existingNews.setContent("Existing content");
        existingNews.setSource(new NewsSource());
        existingNews.setTopics(Collections.singleton(new NewsTopic()));

        when(newsRepository.findById(newsId)).thenReturn(Optional.of(existingNews));
        when(newsSourceService.getById(any(Long.class))).thenReturn(new NewsSource());
        when(newsTopicService.getById(any(Long.class))).thenReturn(new NewsTopic());
        when(newsRepository.save(any(News.class))).thenReturn(existingNews);

        News updatedNews = newsService.update(newsId, existingNews);

        assertEquals(existingNews.getTitle(), updatedNews.getTitle());
        assertEquals(existingNews.getContent(), updatedNews.getContent());
        assertEquals(existingNews.getSource(), updatedNews.getSource());
        assertEquals(existingNews.getTopics(), updatedNews.getTopics());
    }

    @Test
    public void testDeleteNews() {
        Long newsId = 1L;
        newsService.delete(newsId);
        verify(newsRepository).deleteById(newsId);
    }

    @Test
    public void testGetNewsById_ExistingNews_ReturnsNews() {
        Long newsId = 1L;
        News expectedNews = new News();
        expectedNews.setId(newsId);

        when(newsRepository.findById(newsId)).thenReturn(Optional.of(expectedNews));

        News news = newsService.getById(newsId);

        assertEquals(expectedNews, news);
    }

    @Test
    public void testGetNewsById_NonExistingNews_ThrowsResourceNotFoundException() {
        Long newsId = 1L;

        when(newsRepository.findById(newsId)).thenReturn(Optional.empty());

        try {
            newsService.getById(newsId);
        } catch (ResourceNotFoundException ex) {
            assertEquals("News", "News");
            assertEquals("id", "id");
            assertEquals(newsId.toString(), "1");
        }
    }

    @Test
    public void testGetAllNews_ReturnsAllNews() {
        List<News> newsList = Arrays.asList(new News(), new News(), new News());

        Pageable pageable = mock(Pageable.class);

        Page<News> newsPage = mock(Page.class);
        when(newsPage.getContent()).thenReturn(newsList);
        when(newsRepository.findAll(pageable)).thenReturn(newsPage);

        Page<News> resultPage = newsService.getAll(pageable);
        List<News> resultList = resultPage.getContent();

        assertEquals(newsList.size(), resultList.size());
        assertEquals(newsList.get(0), resultList.get(0));
        assertEquals(newsList.get(1), resultList.get(1));
        assertEquals(newsList.get(2), resultList.get(2));
    }

    @Test
    public void testGetNewsBySourceId_ReturnsNewsBySourceId() {
        Long sourceId = 1L;
        List<News> newsList = Arrays.asList(new News(), new News());

        Pageable pageable = mock(Pageable.class);

        Page<News> newsPage = mock(Page.class);
        when(newsPage.getContent()).thenReturn(newsList);
        when(newsRepository.findBySourceId(sourceId, pageable)).thenReturn(newsPage);

        Page<News> resultPage = newsService.getBySourceId(sourceId, pageable);
        List<News> resultList = resultPage.getContent();

        assertEquals(newsList.size(), resultList.size());
        assertEquals(newsList.get(0), resultList.get(0));
        assertEquals(newsList.get(1), resultList.get(1));
    }

    @Test
    public void testGetNewsByTopicId_ReturnsNewsByTopicId() {
        Long topicId = 1L;
        List<News> newsList = Arrays.asList(new News(), new News(), new News());

        Pageable pageable = mock(Pageable.class);

        Page<News> newsPage = mock(Page.class);
        when(newsPage.getContent()).thenReturn(newsList);
        when(newsRepository.findByTopicsId(topicId, pageable)).thenReturn(newsPage);

        Page<News> resultPage = newsService.getByTopicId(topicId, pageable);
        List<News> resultList = resultPage.getContent();

        assertEquals(newsList.size(), resultList.size());
        assertEquals(newsList.get(0), resultList.get(0));
        assertEquals(newsList.get(1), resultList.get(1));
        assertEquals(newsList.get(2), resultList.get(2));
    }
}
