package com.github.jumagaliev1.backendAssignment.service.Impl;

import com.github.jumagaliev1.backendAssignment.model.entity.Role;
import com.github.jumagaliev1.backendAssignment.model.entity.User;
import com.github.jumagaliev1.backendAssignment.model.request.SignInRequest;
import com.github.jumagaliev1.backendAssignment.model.request.SignUpRequest;
import com.github.jumagaliev1.backendAssignment.model.response.JwtResponse;
import com.github.jumagaliev1.backendAssignment.repository.RoleRepository;
import com.github.jumagaliev1.backendAssignment.repository.UserRepository;
import com.github.jumagaliev1.backendAssignment.security.UserDetailsImpl;
import com.github.jumagaliev1.backendAssignment.security.jwt.jwtUtils;
import com.github.jumagaliev1.backendAssignment.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final jwtUtils jwtUtil;

    @Override
    public void registerUser(SignUpRequest request) {
        Set<Role> roles = new HashSet<>();
        if (!roleRepository.existsByName("USER")){
            Role role = new Role();
            role.setName("USER");
            roleRepository.save(role);
        }

        Role userRole = roleRepository.findByName("USER")
                .get();
        roles.add(userRole);

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setRoles(roles);

        userRepository.save(user);
//        log.info("Create new account with role: " + userRole.getName());
    }

    @Override
    public JwtResponse authenticate(SignInRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }
}
