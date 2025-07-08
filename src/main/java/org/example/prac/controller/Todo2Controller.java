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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tododto")
@RequiredArgsConstructor
public class Todo2Controller {

    private final TodoRepository todoRepository;

    private User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }
    @GetMapping
    public String list(HttpSession httpSession, Model model) {
        User user = getCurrentUser(httpSession);

        if(user == null) {
            return "redirect:/login";
        }

        List<Todo> list = todoRepository.findAllByUserId(user.getId());
        model.addAttribute("todos", list);

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

    @GetMapping("/edit/{id}")
    public String update(@PathVariable int id, Model model, HttpSession httpSession) {
        User user = getCurrentUser(httpSession);

        if(user == null) return "redirect:/login";

        Todo todo = todoRepository.findByIdAndUserId(id, user.getId());
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setId(todo.getId());
        todoDTO.setTitle(todo.getTitle());
        todo.setCompleted(todo.isCompleted());

        model.addAttribute("todoDto", todoDTO);

        return "todo-form";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute TodoDTO todoDTO, BindingResult bindingResult, HttpSession httpSession) {
        if(bindingResult.hasErrors()) return "todo-form";

        User user = getCurrentUser(httpSession);
        Todo todo = Todo.builder()
                .id(todoDTO.getId())
                .title(todoDTO.getTitle())
                .completed(todoDTO.isCompleted())
                .userId(user.getId())
                .build();

        todoRepository.update(todo);

        return "redirect:/tododto";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id, HttpSession httpSession) {
        User user = getCurrentUser(httpSession);
        todoRepository.deleteByIdAndUserId(id, user.getId());

        return "redirect:/tododto";
    }
}