package com.example.mycinema.controller;

import com.example.mycinema.common.R;
import com.example.mycinema.domain.dto.PageDTO;
import com.example.mycinema.domain.po.Cinema;
import com.example.mycinema.domain.po.Movie;
import com.example.mycinema.domain.vo.CinemaVO;
import com.example.mycinema.domain.vo.MovieVO;
import com.example.mycinema.query.MovieQuery;
import com.example.mycinema.service.IMovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "电影管理接口")

@RestController
@AllArgsConstructor
public class MovieController {

    @Qualifier("IMovieService")
    private final IMovieService movieService;



    @ApiOperation("根据电影id查询电影的详细信息")
    @GetMapping("/movie/{movieId}")
    public R<MovieVO> getMoiveById(@PathVariable Long movieId){
        return R.success(movieService.getMovieById(movieId));
    }

    @ApiOperation("根据电影名查询电影的详细信息")
    @GetMapping("/searchMovies/{partMovieName}")
    public R<List<MovieVO>> getMoiveByName(@PathVariable String partMovieName) {
        List<MovieVO> movieByName = movieService.getMovieByName(partMovieName);
        return R.success(movieByName);
    }

    @ApiOperation("根据条件分页查询用户接口")
    @GetMapping("/movies/{page}")
    public R<PageDTO<MovieVO>> getMoviesByPage(@PathVariable Integer page){
        MovieQuery query = new MovieQuery();
        query.setPageNo(page);
        PageDTO<MovieVO> moviesPage = movieService.getMoviesByPage(query);
        return R.success(moviesPage);
    }

    @PostMapping("/movie/getCinemasByMovie/{movieId}")
    public R<List<CinemaVO>> getCinemasByMovieId(@PathVariable Long movieId){

        List<CinemaVO> cinemas = movieService.getCinemasByMovieId(movieId);

        return R.success(cinemas);

    }

}
