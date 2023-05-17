package com.github.jumagaliev1.backendAssignment.controller;

import com.github.jumagaliev1.backendAssignment.model.request.SignInRequest;
import com.github.jumagaliev1.backendAssignment.model.request.SignUpRequest;
import com.github.jumagaliev1.backendAssignment.service.AuthService;
import com.github.jumagaliev1.backendAssignment.util.AccountValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService registrationService;
    private final AccountValidator validator;

    @PostMapping("/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity
                    .badRequest().body(result);
        }

        return ResponseEntity.ok(registrationService.authenticate(request));
    }

    @PostMapping("/sign-up")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void registerUser(@Valid @RequestBody SignUpRequest request, BindingResult result) throws Exception {
        validator.validate(request, result);
        if (result.hasErrors())
            throw new Exception("Bad request 400: " + result);

        registrationService.registerUser(request);
    }
}
