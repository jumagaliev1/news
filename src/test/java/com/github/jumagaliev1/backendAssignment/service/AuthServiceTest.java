package com.github.jumagaliev1.backendAssignment.service;

import com.github.jumagaliev1.backendAssignment.model.entity.Role;
import com.github.jumagaliev1.backendAssignment.model.entity.User;
import com.github.jumagaliev1.backendAssignment.model.request.SignInRequest;
import com.github.jumagaliev1.backendAssignment.model.request.SignUpRequest;
import com.github.jumagaliev1.backendAssignment.model.response.JwtResponse;
import com.github.jumagaliev1.backendAssignment.repository.RoleRepository;
import com.github.jumagaliev1.backendAssignment.repository.UserRepository;
import com.github.jumagaliev1.backendAssignment.security.UserDetailsImpl;
import com.github.jumagaliev1.backendAssignment.security.jwt.JwtUtils;
import org.springframework.security.core.Authentication;
import com.github.jumagaliev1.backendAssignment.service.Impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private UserDetailsImpl userDetails;

    private AuthServiceImpl authService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        authService = new AuthServiceImpl(userRepository, roleRepository, passwordEncoder, authenticationManager, jwtUtils);
    }

    @Test
    public void testRegisterUser_SuccessfulRegistration() {
        when(roleRepository.existsByName(anyString())).thenReturn(false);

        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("testUser");
        signUpRequest.setEmail("test@example.com");
        signUpRequest.setPassword("password");

        Role userRole = new Role();
        userRole.setName("USER");
        when(roleRepository.findByName(anyString())).thenReturn(Optional.of(userRole));

        String encodedPassword = "encodedPassword";
        when(passwordEncoder.encode(anyString())).thenReturn(encodedPassword);

        authService.registerUser(signUpRequest);

         verify(userRepository).save(argThat(user ->
                user.getUsername().equals(signUpRequest.getUsername())
                        && user.getEmail().equals(signUpRequest.getEmail())
                        && user.getPassword().equals(encodedPassword)
                        && user.getRoles().contains(userRole)
        ));
    }

    @Test
    public void testAuthenticate_SuccessfulAuthentication() {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUsername("testUser");
        signInRequest.setPassword("password");

        User user = new User();
        user.setId(1L);
        user.setUsername(signInRequest.getUsername());
        user.setEmail("test@example.com");

        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);  // Replace userDetails with the appropriate value

        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(authentication);

        String jwtToken = "jwtToken";
        List<String> roles = List.of("USER");
        JwtResponse expectedJwtResponse = new JwtResponse(jwtToken, user.getId(), user.getUsername(), user.getEmail(), roles);

        when(jwtUtils.generateJwtToken(any(Authentication.class))).thenReturn(jwtToken);

        JwtResponse jwtResponse = authService.authenticate(signInRequest);

        assertNotNull(jwtResponse);
        assertEquals(expectedJwtResponse.getToken(), jwtResponse.getToken());
        assertEquals(expectedJwtResponse.getId(), jwtResponse.getId());
        assertEquals(expectedJwtResponse.getUsername(), jwtResponse.getUsername());
        assertEquals(expectedJwtResponse.getEmail(), jwtResponse.getEmail());
        assertEquals(expectedJwtResponse.getRoles(), jwtResponse.getRoles());

        verify(authenticationManager).authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));

        verify(jwtUtils).generateJwtToken(authentication);

        verify(authentication).getPrincipal();
    }
}
