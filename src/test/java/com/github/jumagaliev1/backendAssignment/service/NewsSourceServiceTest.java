package com.github.jumagaliev1.backendAssignment.service;

import com.github.jumagaliev1.backendAssignment.model.entity.NewsSource;
import com.github.jumagaliev1.backendAssignment.repository.NewsSourceRepository;
import com.github.jumagaliev1.backendAssignment.service.Impl.NewsSourceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class NewsSourceServiceTest {
    @Mock
    private NewsSourceRepository newsSourceRepository;

    private NewsSourceImpl newsSourceService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        newsSourceService = new NewsSourceImpl(newsSourceRepository);
    }

    @Test
    public void testCreateNewsSource() {
        NewsSource source = new NewsSource();
        source.setId(1L);
        source.setName("Sample Source");

        when(newsSourceRepository.save(any(NewsSource.class))).thenReturn(source);

        NewsSource createdSource = newsSourceService.create(source);

        assertNotNull(createdSource);
        assertEquals(source.getId(), createdSource.getId());
        assertEquals(source.getName(), createdSource.getName());

        verify(newsSourceRepository).save(source);
    }

    @Test
    public void testDeleteNewsSource() {
        newsSourceService.delete(1L);

        verify(newsSourceRepository).deleteById(1L);
    }

    @Test
    public void testGetNewsSourceById_ExistingSource_ReturnsSource() {
        NewsSource source = new NewsSource();
        source.setId(1L);
        source.setName("Sample Source");

        when(newsSourceRepository.findById(anyLong())).thenReturn(Optional.of(source));

        NewsSource resultSource = newsSourceService.getById(source.getId());

        assertNotNull(resultSource);
        assertEquals(source.getId(), resultSource.getId());
        assertEquals(source.getName(), resultSource.getName());

        verify(newsSourceRepository).findById(source.getId());
    }

    @Test
    public void testGetAllNewsSources() {
        Page<NewsSource> sourcePage = mock(Page.class);
        when(sourcePage.getContent()).thenReturn(Collections.singletonList(new NewsSource()));
        when(newsSourceRepository.findAll(any(Pageable.class))).thenReturn(sourcePage);

        Page<NewsSource> resultPage = newsSourceService.getAll(mock(Pageable.class));

        assertNotNull(resultPage);
        assertEquals(1, resultPage.getContent().size());

        verify(newsSourceRepository).findAll(any(Pageable.class));
    }

}
