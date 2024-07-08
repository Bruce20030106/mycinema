package com.example.mycinema.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
public class Movie {
    @TableId(type = IdType.AUTO)
    private Long movieId;

    private String movieName;

    private String posterName;//the name of the image of the moive

    private String summary;

    private Long showTime;

    private String actors;

    private Integer genre;

    private Double rate;


}
