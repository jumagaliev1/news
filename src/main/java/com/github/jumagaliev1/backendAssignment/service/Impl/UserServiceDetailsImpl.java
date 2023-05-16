package com.github.jumagaliev1.backendAssignment.service.Impl;

import com.github.jumagaliev1.backendAssignment.model.entity.User;
import com.github.jumagaliev1.backendAssignment.repository.UserRepository;
import com.github.jumagaliev1.backendAssignment.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceDetailsImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found!"));
        return UserDetailsImpl.build(user);
    }
}
