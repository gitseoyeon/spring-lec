package org.example.prac.controller;

import lombok.RequiredArgsConstructor;
import org.example.prac.model.Teacher;
import org.example.prac.repository.TeacherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherRepository teacherRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("teachers", teacherRepository.findAll());
        return "teacher-list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacher-form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Teacher teacher) {
        teacherRepository.save(teacher);

        return "redirect:/teachers";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        model.addAttribute("teacher", teacherRepository.findById(id));

        return "teacher-form";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Teacher teacher) {
        teacherRepository.update(teacher);

        return "redirect:/teachers";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        teacherRepository.delete(id);
        return "redirect:/teachers";
    }

}
