package com.example.mycinema.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mycinema.common.StringToListUtil;
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
import org.redisson.api.RBloomFilter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements IMovieService {

    private final MovieMapper movieMapper;

    private final CinemaMapper cinemaMapper;

    private RedisTemplate<String, Object> redisTemplate;
    private static final String MOVIE_CACHE_PREFIX = "movie:";

    private static final String MOVIE_PAGE_CACHE_PREFIX = "moviePage:";

    private RBloomFilter<Long> movieBloomFilter;

    @Override
    public MovieVO getMovieById(Long movieId) {
        String cacheKey = MOVIE_CACHE_PREFIX + movieId;
        System.out.println("Redis中电影键为:" + cacheKey);

        // 使用布隆过滤器检查键是否存在
        if (!movieBloomFilter.contains(movieId)) {
            System.out.println("布隆过滤器中不存在该电影ID:" + movieId);
            return null;
        }

        Movie movie = (Movie) redisTemplate.opsForValue().get(cacheKey);
        if (movie == null) {
            System.out.println("redis中未有数据，前往mysql数据库查询");
            movie = movieMapper.selectById(movieId);
            if (movie != null) {
                redisTemplate.opsForValue().set(cacheKey, movie, 1, TimeUnit.HOURS);
                // 将新查询到的电影ID添加到布隆过滤器中
                movieBloomFilter.add(movieId);
            } else {
                System.out.println("MySQL中也未找到该电影ID:" + movieId);
            }
        }

        return BeanUtil.copyProperties(movie, MovieVO.class);
    }

    @Override
    public PageDTO<MovieVO> getMoviesByPage(MovieQuery query) {
        String actor = query.getActor();
        Integer rate = query.getRate();
        String movieName = query.getMovieName();
        Integer genre = query.getGenre();

        //拿到分页对象
        Page<Movie> page = query.toMpPageDefaultSortByRate();

        String pagekey = MOVIE_PAGE_CACHE_PREFIX + query.getPageNo();
        System.out.println("Redis中电影分页键为:"+pagekey);

        Page<Movie> moviePage = (Page<Movie>) redisTemplate.opsForValue().get(pagekey);

        if (moviePage == null){
            System.out.println("redis中未有数据，前往mysql数据库查询");
            moviePage = lambdaQuery()
                    .like(movieName != null, Movie::getMovieName, movieName)
                    .like(actor != null, Movie::getActors, actor)
                    .eq(genre != null, Movie::getGenre, genre)
                    .eq(rate != null, Movie::getRate, rate)
                    .page(page);
            if (moviePage != null){
                redisTemplate.opsForValue().set(pagekey,moviePage,1,TimeUnit.HOURS);
            }
        }

        return PageDTO.of(moviePage, movie ->{
            MovieVO vo = BeanUtil.copyProperties(movie, MovieVO.class);
            return vo;
        });

    }

    @Override
    public List<CinemaVO> getCinemasByMovieId(Long movieId) {

        List<Cinema> cinemas = cinemaMapper.selectList(null);

        List<CinemaVO> cinemaVOS = cinemas.stream()
                .filter(cinema -> StringToListUtil.stringToList(cinema.getShowingMovieIds()).contains(movieId))
                .map(cinema -> BeanUtil.copyProperties(cinema, CinemaVO.class))
                .collect(Collectors.toList());

        return cinemaVOS;
    }

    @Override
    public List<Long> getAllMovieIds() {
        List<Movie> movies = movieMapper.selectList(null);
        List<Long> ids = movies.stream().map(movie -> {
            Long movieId = movie.getMovieId();
            return movieId;
        }).collect(Collectors.toList());
        return ids;
    }

    @Override
    public List<MovieVO> getMovieByName(String partMovieName) {
        List<Movie> list = lambdaQuery().like(Movie::getMovieName, partMovieName).list();
        List<MovieVO> movieVOS = BeanUtil.copyToList(list, MovieVO.class);
        return movieVOS;
    }
}
