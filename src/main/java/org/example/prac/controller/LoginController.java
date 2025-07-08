package org.example.prac.controller;

import lombok.RequiredArgsConstructor;
import org.example.prac.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserRepository userRepository;

    @GetMapping({"/", "/login"})
    public String showLogin() {
        return "login";
    }
}
