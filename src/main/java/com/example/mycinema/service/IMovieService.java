package com.example.mycinema.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mycinema.domain.dto.PageDTO;
import com.example.mycinema.domain.po.Movie;
import com.example.mycinema.domain.vo.MovieVO;
import com.example.mycinema.query.MovieQuery;
import org.springframework.stereotype.Service;

@Service
public interface IMovieService extends IService<Movie> {
    Movie getMovieById(Long id);

    PageDTO<MovieVO> getMoviesByPage(MovieQuery query);
}
