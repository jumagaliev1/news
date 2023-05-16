package com.github.jumagaliev1.backendAssignment.util;

import com.github.jumagaliev1.backendAssignment.model.request.SignUpRequest;
import com.github.jumagaliev1.backendAssignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class AccountValidator implements Validator {

    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return SignUpRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignUpRequest request = (SignUpRequest) target;
        String regexEmail = "^[\\w]+(?:\\.[\\w]+)*@(?:[a-z0-9-]+\\.)+[\\\\a-zA-Z]{2,6}";
        Pattern patternEmail = Pattern.compile(regexEmail);

        if (!patternEmail.matcher(request.getEmail()).matches())
            errors.rejectValue("email","","Email address is invalid! Format:example@example.com");

        if (userRepository.existsByEmail(request.getEmail()))
            errors.rejectValue("email","","Email is already taken!");

        if (userRepository.existsByUsername(request.getUsername()))
            errors.rejectValue("username","","Username is already taken!");

    }
}