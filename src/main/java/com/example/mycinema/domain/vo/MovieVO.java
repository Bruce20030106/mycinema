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

    private String posterName;

//    private Long showTime;

    private String actors;

    private Integer genre;

    private Double rate;


}