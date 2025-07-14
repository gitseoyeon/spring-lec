package org.example.prac.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.prac.dto.LoginDto;
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
public class LoginController {
    private final UserTodoRepository userTodoRepository;
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginDto", new LoginDto());

        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginDto loginDto, BindingResult bindingResult,
                        HttpSession httpSession, Model model) {
        if(bindingResult.hasErrors()) return "login";

        User_todo userTodo = userTodoRepository.findByUsername(loginDto.getUsername()).orElse(null);

        // 아이디를 잘못 입력하거나 비밀번호를 잘못입력했을 경우
        if(userTodo == null || !userTodo.getPassword().equals(loginDto.getPassword())) {
            model.addAttribute("error", "아이디, 비밀번호가 올바르지 않습니다.");

            return "login";
        }

        httpSession.setAttribute("user", userTodo);

        return "redirect:/posts";
    }
}
