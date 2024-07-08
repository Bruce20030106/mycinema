package com.example.mycinema.domain.po;

import java.util.List;
import java.util.Map;

public class Cinema {
    private Long cinemaId;
    private String cinemaName;
    private String location;
    private List<Long> showingMovieIds; // 上映电影的id列表
    private Map<String,Integer> ticketPrices; //电影名和电影票价的映射


}
