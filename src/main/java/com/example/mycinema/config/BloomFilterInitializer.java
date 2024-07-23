package com.example.mycinema.config;

import com.example.mycinema.mapper.MovieMapper;
import com.example.mycinema.service.IMovieService;
import lombok.AllArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class BloomFilterInitializer implements CommandLineRunner {


    private RBloomFilter<Long> movieBloomFilter;

    @Qualifier("IMovieService")
    private IMovieService movieService;

    @Override
    public void run(String... args) throws Exception {
        List<Long> movieIds = movieService.getAllMovieIds();
        for (Long movieId : movieIds) {
            movieBloomFilter.add(movieId);
        }
        System.out.println("布隆过滤器已预热完成");
    }
}

