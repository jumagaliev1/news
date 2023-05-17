package com.github.jumagaliev1.backendAssignment.service;

import com.github.jumagaliev1.backendAssignment.model.entity.User;
import com.github.jumagaliev1.backendAssignment.repository.UserRepository;
import com.github.jumagaliev1.backendAssignment.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void testGetUserById_ExistingUser_ReturnsUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        User resultUser = userService.getById(user.getId());

        assertNotNull(resultUser);
        assertEquals(user.getId(), resultUser.getId());
        assertEquals(user.getUsername(), resultUser.getUsername());

        verify(userRepository).findById(user.getId());
    }

    @Test
    public void testGetUserById_NonExistingUser_ThrowsEntityNotFoundException() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> userService.getById(1L));

        verify(userRepository).findById(1L);
    }

    @Test
    public void testFindByUsername_ExistingUser_ReturnsUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        User resultUser = userService.findByUsername(user.getUsername());

        assertNotNull(resultUser);
        assertEquals(user.getId(), resultUser.getId());
        assertEquals(user.getUsername(), resultUser.getUsername());

        verify(userRepository).findByUsername(user.getUsername());
    }

    @Test
    public void testFindByUsername_NonExistingUser_ThrowsUsernameNotFoundException() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class,
                () -> userService.findByUsername("nonExistingUser"));

        verify(userRepository).findByUsername("nonExistingUser");
    }
}
