package org.example.prac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/eduPage")
    public String index() {
        return "eduindex";
    }
}
