package com.example.mycinema.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mycinema.domain.po.Movie;
import org.springframework.stereotype.Service;

@Service
public interface IMovieService extends IService<Movie> {
    Movie getMovieById(Long id);
}
