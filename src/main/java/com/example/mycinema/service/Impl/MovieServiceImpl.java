package com.example.mycinema.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mycinema.common.R;
import com.example.mycinema.domain.dto.PageDTO;
import com.example.mycinema.domain.po.Cinema;
import com.example.mycinema.domain.po.Movie;
import com.example.mycinema.domain.vo.CinemaVO;
import com.example.mycinema.domain.vo.MovieVO;
import com.example.mycinema.mapper.CinemaMapper;
import com.example.mycinema.mapper.MovieMapper;
import com.example.mycinema.query.MovieQuery;
import com.example.mycinema.service.IMovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements IMovieService {

    private final CinemaMapper cinemaMapper;
    @Override
    public MovieVO getMovieById(Long movieId) {
        Movie movie = getById(movieId);

        MovieVO vo = BeanUtil.copyProperties(movie, MovieVO.class);
        return vo;
    }

    @Override
    public PageDTO<MovieVO> getMoviesByPage(MovieQuery query) {
        String actor = query.getActor();
        Integer rate = query.getRate();
        String movieName = query.getMovieName();
        Integer genre = query.getGenre();

        //拿到分页对象
        Page<Movie> page = query.toMpPageDefaultSortByRate();

        Page<Movie> pageWithData = lambdaQuery()
                .like(movieName != null, Movie::getMovieName, movieName)
                .like(actor != null, Movie::getActors, actor)
                .eq(genre != null, Movie::getGenre, genre)
                .eq(rate != null, Movie::getRate, rate)
                .page(page);

        return PageDTO.of(pageWithData, movie ->{
            MovieVO vo = BeanUtil.copyProperties(movie, MovieVO.class);
            return vo;
        });

    }

    @Override
    public List<CinemaVO> getCinemasByMovieId(Long movieId) {

        List<Cinema> cinemas = cinemaMapper.selectList(null);

        List<CinemaVO> cinemaVOS = cinemas.stream()
                .filter(cinema -> cinema.getShowingMovieIds().contains(movieId))
                .map(cinema -> BeanUtil.copyProperties(cinema, CinemaVO.class))
                .collect(Collectors.toList());

        return cinemaVOS;
    }
}
