package org.example.prac.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("org.example.prac")
@EnableAspectJAutoProxy // AOP 기능
public class DemoConfig {
}
