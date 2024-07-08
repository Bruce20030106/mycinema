package com.example.mycinema;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.mycinema.mapper")
@SpringBootApplication
public class MycinemaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MycinemaApplication.class, args);
    }

}
