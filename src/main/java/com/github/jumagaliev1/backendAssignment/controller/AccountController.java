package com.github.jumagaliev1.backendAssignment.controller;

import com.github.jumagaliev1.backendAssignment.model.request.SignUpRequest;
import com.github.jumagaliev1.backendAssignment.service.AuthService;
import com.github.jumagaliev1.backendAssignment.util.AccountValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AccountController {

    private final AuthService registrationService;
    private final AccountValidator validator;

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }


    @GetMapping("/sign-up")
    public String createAccountPage(@ModelAttribute("userRequest")SignUpRequest request) {
        return "auth/sign-up";
    }

    @PostMapping("/sign-up")
    public String createAccount(@ModelAttribute("userRequest") @Valid SignUpRequest request,
                                BindingResult result) {
        validator.validate(request,result);

        if (result.hasErrors()) {
            System.out.println(result);
            return "/auth/sign-up";
        }

        registrationService.registerUser(request);
        return "redirect:/auth/login";
    }
}
