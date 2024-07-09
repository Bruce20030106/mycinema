package com.example.mycinema.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("new_big_movie")
public class Movie {
    @TableId(type = IdType.AUTO)
    private Long movieId;

    private String movieName;

    @TableField("poster_image")
    private String posterName;//the name of the image of the moive

    @TableField("description")
    private String summary;

//    private Long showTime;

    private String actors;

    @TableField("movie_types")
    private Integer genre;

    private Double rate;


}
