package com.example.mycinema.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("User")
public class User {
    @TableId(type = IdType.AUTO)
    private Long userId;

    private String userName;

    private String password;

    private String gender;

    private Integer age;

}
