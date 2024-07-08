package com.example.mycinema.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class UserVO {

    private Long userId;

    private String userName;

    private String password;

    private String gender;

    private Integer age;

}