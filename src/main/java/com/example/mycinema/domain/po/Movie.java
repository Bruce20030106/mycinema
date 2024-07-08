package com.example.mycinema.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Data
@TableName(value = "new_big_movie")
public class Movie {
    @TableId(type = IdType.AUTO)
    private Long movieId;

    private String movieName;

    private String posterName;//the name of the image of the moive

    @TableField("description")
    private String summary;

    @TableField("release_date")
    private Long showTime;

    private String actors;

    @TableField("movie_types")
    private String movieTypes;

    private Double rate;


}
