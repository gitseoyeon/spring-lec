package org.example.prac.repository;

import org.springframework.stereotype.Repository;

@Repository
public class DemoRepository {
    public void save() {
        System.out.println("저장 로직 실행");
    }
}
