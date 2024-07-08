package com.example.mycinema.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Data

public class MovieVO {

    private Long movieId;

    private String movieName;

    private String summary;

    private Long showTime;

    private List<String> actors;

    private List<String> movieTypes;

    private Integer rate;


}