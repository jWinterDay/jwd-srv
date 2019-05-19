package com.jwd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.jwd")
@ServletComponentScan
public class JwdSrvApplication {
    public static void main(String[] args) {
        SpringApplication.run(JwdSrvApplication.class, args);
    }
}
