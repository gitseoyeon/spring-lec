package org.example.prac.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.prac.dto.TodoDTO;
import org.example.prac.model.Todo;
import org.example.prac.model.User;
import org.example.prac.repository.TodoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tododto")
@RequiredArgsConstructor
public class Todo2Controller {

    private final TodoRepository todoRepository;

    private User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }
    @GetMapping
    public String list(HttpSession httpSession) {
        User user = getCurrentUser(httpSession);

        if(user == null) {
            return "redirect:/login";
        }

        return "todo-list";
    }

    @GetMapping("/add")
    public String addForm(HttpSession httpSession, Model model) {
        if(getCurrentUser(httpSession) == null) return "redirect:/login";

        model.addAttribute("todoDto", new TodoDTO());

        return "todo-form";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute TodoDTO todoDTO, BindingResult bindingResult, HttpSession httpSession) {
        if(bindingResult.hasErrors()) return "todo-form";

        User user = getCurrentUser(httpSession);
        Todo todo = Todo.builder()
                .userId(user.getId())
                .title(todoDTO.getTitle())
                .completed(todoDTO.isCompleted())
                .build();

        todoRepository.save(todo);

        return "redirect:/tododto";
    }
}