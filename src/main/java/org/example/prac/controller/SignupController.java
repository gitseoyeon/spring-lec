package org.example.prac.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.prac.dto.SignupDto;
import org.example.prac.model.User_todo;
import org.example.prac.repository.UserTodoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignupController {
    private final UserTodoRepository userTodoRepository;

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("signupDto", new SignupDto());

        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute SignupDto signupDto, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) return "signup";

        if(userTodoRepository.findByUsername(signupDto.getUsername()).isPresent()) {
            model.addAttribute("error", "이미 사용중인 아이디 입니다.");

            return "signup";
        }

        userTodoRepository.save(User_todo.builder()
                .username(signupDto.getUsername())
                .password(signupDto.getPassword())
                .build());

        return "redirect:/login?registered";
    }
}
