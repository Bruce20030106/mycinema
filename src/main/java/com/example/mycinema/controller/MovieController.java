package com.example.mycinema.controller;

import com.example.mycinema.domain.po.Movie;
import com.example.mycinema.service.IMovieService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Api(tags = "电影管理接口")
@RequestMapping("/movies")
@RestController
@RequiredArgsConstructor
public class MovieController {

    private final IMovieService movieService;

    @GetMapping("/{id}")
    public Movie getMoiveById(@PathVariable Long id){
        return movieService.getMovieById(id);
    }



}
