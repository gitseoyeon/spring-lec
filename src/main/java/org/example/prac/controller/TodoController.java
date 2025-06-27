package org.example.prac.controller;

import org.example.prac.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/todoPage")
    public String showToDoList(Model model) {
        model.addAttribute("todos", todoService.getAllTasks());

        return "todolist";}

    @PostMapping("/todos")
    public String addTask(@RequestParam("task") String task) {
        todoService.addTask(task);

        return "redirect:/todoPage";
    }

    @PostMapping("/todos/delete")
    public String deleteTask(@RequestParam("taskId") int taskId) {
        todoService.deleteTask(taskId);
        return "redirect:/todoPage";
    }

    @PostMapping("/todos/toggle")
    public String toggleComplete(@RequestParam("taskId") int taskId) {
        todoService.toggleTaskCompletion(taskId);
        return "redirect:/todoPage";
    }

    @PostMapping("/todos/update")
    public String updateTask(@RequestParam("taskId") int taskId,
                             @RequestParam("newDescription") String newDescription) {
        todoService.updateTask(taskId, newDescription);
        return "redirect:/todoPage";
    }
}
