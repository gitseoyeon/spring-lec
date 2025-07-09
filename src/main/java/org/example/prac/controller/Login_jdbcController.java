package org.example.prac.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.prac.dto.Login_jdbcDTO;
import org.example.prac.model.User_jdbc;
import org.example.prac.repository.User_jdbcRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class Login_jdbcController {
    private final User_jdbcRepository userJdbcRepository;

    @GetMapping( "/login-jdbc")
    public String showLogin(Model model) {
        model.addAttribute("loginJdbcDto", new Login_jdbcDTO());

        return "login-jdbc";
    }

    @PostMapping("/login-jdbc")
    public String doLogin(
            @Valid @ModelAttribute Login_jdbcDTO loginJdbcDto,
            BindingResult bindingResult,
            HttpSession httpSession,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "login-jdbc";
        }

        try {
            User_jdbc userJdbc = userJdbcRepository.findByUsername(loginJdbcDto.getUsername());

            if(!userJdbc.getPassword().equals(loginJdbcDto.getPassword())) {
                model.addAttribute("error", "비밀번호가 올바르지 않습니다");

                return "login-jdbc";
            }

            httpSession.setAttribute("user", userJdbc);

            return "redirect:/tododto";
        } catch(Exception e) {
            model.addAttribute("error", "존재하지 않는 사용자입니다.");

            return "login-jdbc";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/login";
    }
}
