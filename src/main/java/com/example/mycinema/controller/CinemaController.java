package com.example.mycinema.controller;

import com.example.mycinema.common.R;
import com.example.mycinema.domain.vo.CinemaVO;
import com.example.mycinema.domain.vo.MovieVO;
import com.example.mycinema.service.ICinemaService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "电影院管理接口")
@RestController
@AllArgsConstructor
public class CinemaController {

    private final ICinemaService cinemaService;

    @PostMapping("/getAllCinemas")
    public R<List<CinemaVO>> getAllCinemas(){

        List<CinemaVO> cinemaVOList = cinemaService.getAllCinema();

        return R.success(cinemaVOList);
    }

    @GetMapping("/cinema")
    public R<CinemaVO> getCinemaById(@RequestParam Long id){
        return R.success(cinemaService.getCinemaById(id));
    }

    @PostMapping("/cinema/getMoviesByCinema/{cinema_id}")
    public R<List<MovieVO>> getMoviesByCinemaId(@PathVariable Long cinema_id){

        List<MovieVO> showingMovies = cinemaService.getMoviesByCinemaId(cinema_id);

        return R.success(showingMovies);

    }


}
