package com.example.mycinema.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mycinema.common.R;
import com.example.mycinema.domain.dto.PageDTO;
import com.example.mycinema.domain.po.Movie;
import com.example.mycinema.domain.vo.CinemaVO;
import com.example.mycinema.domain.vo.MovieVO;
import com.example.mycinema.query.MovieQuery;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMovieService extends IService<Movie> {
    MovieVO getMovieById(Long movieId);

    PageDTO<MovieVO> getMoviesByPage(MovieQuery query);

    List<CinemaVO> getCinemasByMovieId(Long movieId);

    List<Long> getAllMovieIds();
}
