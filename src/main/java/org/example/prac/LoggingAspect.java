package org.example.prac;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.example.demo..*(..))")
    public void logBefore(JoinPoint jp) {
        System.out.println("[LOG] 메소드 호출: " + jp.getSignature());
    }
}
