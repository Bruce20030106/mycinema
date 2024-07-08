package com.example.mycinema.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CinemaVO {

    private Long cinemaId;

    private String cinemaName;

    private String pictureName;//the name of the image of the cinema

    private String location;

    private List<Integer> showingMovieIds; // 上映电影的id列表

    private List<Map<Integer,Integer>> ticketPrices; //电影名和电影票价的映射


}
