package com.example.mycinema.controller;

import com.example.mycinema.domain.dto.PageDTO;
import com.example.mycinema.domain.po.Movie;
import com.example.mycinema.domain.vo.MovieVO;
import com.example.mycinema.query.MovieQuery;
import com.example.mycinema.service.IMovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Api(tags = "电影管理接口")
@RequestMapping("/movies")
@RestController
@AllArgsConstructor
public class MovieController {

    private final IMovieService movieService;

    @GetMapping("/{id}")
    public MovieVO getMoiveById(@PathVariable Long id){
        return movieService.getMovieById(id);
    }

    @ApiOperation("根据条件分页查询用户接口")
    @GetMapping("/page")
    public PageDTO<MovieVO> getMoviesByPage(MovieQuery query){
        return movieService.getMoviesByPage(query);
    }



}
