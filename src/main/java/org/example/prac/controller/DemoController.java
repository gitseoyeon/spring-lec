package org.example.prac.controller;

import org.example.prac.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DemoController {
    // 생성자 주입
//    private final DemoService demoService;
//
//    @Autowired
//    public DemoController(DemoService demoService) {
//        this.demoService = demoService;
//    }

    // 필드 주입
//    @Autowired
//    private DemoService demoService;

    // 수정자 주입
    private DemoService demoService;
    @Autowired
    public void setDemoService(DemoService demoService) {
        this.demoService = demoService;
    }
}
