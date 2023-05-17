package com.github.jumagaliev1.backendAssignment.service;

import com.github.jumagaliev1.backendAssignment.exception.ResourceNotFoundException;
import com.github.jumagaliev1.backendAssignment.model.entity.NewsTopic;
import com.github.jumagaliev1.backendAssignment.repository.NewsTopicRepository;
import com.github.jumagaliev1.backendAssignment.service.Impl.NewsTopicServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class NewsTopicServieTest {

    @Mock
    private NewsTopicRepository newsTopicRepository;

    private NewsTopicServiceImpl newsTopicService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        newsTopicService = new NewsTopicServiceImpl(newsTopicRepository);
    }

    @Test
    public void testCreateNewsTopic() {
        NewsTopic topic = new NewsTopic();
        topic.setId(1L);
        topic.setName("Technology");

        when(newsTopicRepository.save(any(NewsTopic.class))).thenReturn(topic);

        NewsTopic createdTopic = newsTopicService.create(topic);

        assertNotNull(createdTopic);
        assertEquals(topic.getId(), createdTopic.getId());
        assertEquals(topic.getName(), createdTopic.getName());

        verify(newsTopicRepository).save(topic);
    }

    @Test
    public void testUpdateNewsTopic_NonExistingTopic_ThrowsResourceNotFoundException() {
        NewsTopic updatedTopic = new NewsTopic();
        updatedTopic.setName("Science");

        when(newsTopicRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> newsTopicService.update(1L, updatedTopic));

        verify(newsTopicRepository).findById(1L);
    }

    @Test
    public void testDeleteNewsTopic() {
        newsTopicService.delete(1L);

        verify(newsTopicRepository).deleteById(1L);
    }

    @Test
    public void testGetNewsTopicById_ExistingTopic_ReturnsTopic() {
        NewsTopic topic = new NewsTopic();
        topic.setId(1L);
        topic.setName("Technology");

        when(newsTopicRepository.findById(anyLong())).thenReturn(Optional.of(topic));

        NewsTopic resultTopic = newsTopicService.getById(topic.getId());

        assertNotNull(resultTopic);
        assertEquals(topic.getId(), resultTopic.getId());
        assertEquals(topic.getName(), resultTopic.getName());

        verify(newsTopicRepository).findById(topic.getId());
    }

    @Test
    public void testGetNewsTopicById_NonExistingTopic_ThrowsResourceNotFoundException() {
        when(newsTopicRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> newsTopicService.getById(1L));

        verify(newsTopicRepository).findById(1L);
    }

    @Test
    public void testGetAllNewsTopics() {
        Page<NewsTopic> topicPage = mock(Page.class);
        when(topicPage.getContent()).thenReturn(Collections.singletonList(new NewsTopic()));
        when(newsTopicRepository.findAll(any(Pageable.class))).thenReturn(topicPage);

        Page<NewsTopic> resultPage = newsTopicService.getAll(mock(Pageable.class));

        assertNotNull(resultPage);
        assertEquals(1, resultPage.getContent().size());

        verify(newsTopicRepository).findAll(any(Pageable.class));
    }
}
