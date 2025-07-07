package org.example.prac.controller;

import lombok.RequiredArgsConstructor;
import org.example.prac.model.Student;
import org.example.prac.model.Teacher;
import org.example.prac.repository.StudentRepository;
import org.example.prac.repository.TeacherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("students", studentRepository.findAll());

        return "student-list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        List<Teacher> teachers = teacherRepository.findAll();
        model.addAttribute("student", new Student()); // 새로 생성된 학생 빈 객체
        model.addAttribute("teachers", teachers);

        return "student-form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Student student) {
        studentRepository.save(student);

        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        Student student = studentRepository.findById(id);
        List<Teacher> teachers = teacherRepository.findAll();

        model.addAttribute("student", student);
        model.addAttribute("teachers", teachers);
        return "student-form";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Student student) {
        studentRepository.update(student);
        return "redirect:/students";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        studentRepository.delete(id);
        return "redirect:/students";
    }
}
