package com.example.mycinema.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mycinema.domain.po.Movie;
import com.example.mycinema.mapper.MovieMapper;
import com.example.mycinema.service.IMovieService;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements IMovieService {
    @Override
    public Movie getMovieById(Long id) {
        Movie movie = getById(id);
        return movie;
    }
}
