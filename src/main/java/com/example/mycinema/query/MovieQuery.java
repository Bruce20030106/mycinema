package com.example.mycinema.query;

import com.example.mycinema.enums.MovieGenre;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MovieQuery extends PageQuery{

    @ApiModelProperty("电影名")
    private String movieName;

    @ApiModelProperty("电影类型")
    private MovieGenre genre;

    @ApiModelProperty("电影评分")
    private Integer rate;

    @ApiModelProperty("演员")
    private String actor;


}
