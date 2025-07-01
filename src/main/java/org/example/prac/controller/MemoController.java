package org.example.prac.controller;

import org.example.prac.repository.MemoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MemoController {
    private final MemoRepository memoRepository;

    public MemoController(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @GetMapping("/memoPage")
    public String listMemos(Model model) {
        model.addAttribute("memos", memoRepository.findAll());
        return "memo-list";
    }

    @GetMapping("/editMemo/{id}")
    public String editMemo(@PathVariable int id, Model model) {
        model.addAttribute(memoRepository.findById(id));
        return "memo-edit";
    }

    @PostMapping("/addMemo")
    public String addMemo(@RequestParam String title, @RequestParam String content) {
        memoRepository.save(title, content);

        return "redirect:/memoPage";
    }

    @PostMapping("/editMemo")
    public String editMemo(@RequestParam int id, @RequestParam String title, @RequestParam String content) {
        memoRepository.update(id, title, content);

        return "redirect:/memoPage";
    }

    @PostMapping("/deleteMemo")
    public String deleteMemo(@RequestParam int id) {
        memoRepository.delete(id);

        return "redirect:/memoPage";
    }
}
