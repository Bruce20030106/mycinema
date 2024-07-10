package com.example.mycinema.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mycinema.domain.po.Cinema;
import com.example.mycinema.domain.vo.CinemaVO;
import com.example.mycinema.domain.vo.MovieVO;
import com.example.mycinema.mapper.CinemaMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICinemaService extends IService<Cinema> {

    List<CinemaVO> getAllCinema();

    CinemaVO getCinemaById(Long id);

    List<MovieVO> getMoviesByCinemaId(Long cinemaId);
}
