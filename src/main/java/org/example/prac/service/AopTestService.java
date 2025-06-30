package org.example.prac.service;

import org.springframework.stereotype.Component;

@Component
public class AopTestService {
    public void perform() {
        System.out.println("서비스 로직 실행");
    }
}
