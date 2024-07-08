package com.example.mycinema.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Long userId;

    private String userName;

    private String password;

    private String gender;

    private Integer age;

    private List<String> prefernece;

}
