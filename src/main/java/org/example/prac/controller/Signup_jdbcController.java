package org.example.prac.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.prac.dto.Signup_jdbcDTO;
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
public class Signup_jdbcController {
    private final User_jdbcRepository userJdbcRepository;

    @GetMapping("/signup-jdbc")
    public String showSignup(Model model) {
        model.addAttribute("signupJdbcDto", new Signup_jdbcDTO());
        return "signup-jdbc";
    }

    @PostMapping("/signup-jdbc")
    public String doSignup(
            @Valid @ModelAttribute Signup_jdbcDTO signupJdbcDto,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "signup-jdbc";
        }

        if(userJdbcRepository.findByUsername(signupJdbcDto.getUsername()) != null) {
            model.addAttribute("error", "이미 사용중인 아이디입니다.");

            return "signup-jdbc";
        }

        // 중복 가입 여부 체크

        User_jdbc userJdbc = User_jdbc.builder()
                .username(signupJdbcDto.getUsername())
                .password(signupJdbcDto.getPassword())
                .build();
        userJdbcRepository.save(userJdbc);

        return "redirect:/login?registered";
    }
}
