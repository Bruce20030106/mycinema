package com.example.mycinema.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.mycinema.enums.MovieGenre;
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

    private Long showTime;

    private List<String> actors;

    private MovieGenre genre;

    private Integer rate;


}