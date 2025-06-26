package org.example.prac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/helloPage")
    public String hello() {
        return "hello";
    }
    @GetMapping("/myPage")
    public String myPage() {
        return "myPage";
    }
}
