package com.example.mycinema.domain.po;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Movie {
    private Long movieId;
    private String movieName;

    private String summary;

    private LocalDateTime showTime;

    private List<String> actors;

    private List<String> movieType;

    private String movieScore;


}
