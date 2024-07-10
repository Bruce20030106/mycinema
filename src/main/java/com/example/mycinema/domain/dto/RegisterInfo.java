package com.example.mycinema.domain.dto;

import lombok.Data;

@Data
public class RegisterInfo {
    private Long userId;
    private String userName;
    private String password;
    private String gender;
    private int age;


}
