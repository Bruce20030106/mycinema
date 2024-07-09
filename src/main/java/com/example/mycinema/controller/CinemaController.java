package com.example.mycinema.controller;

import com.example.mycinema.domain.vo.CinemaVO;
import com.example.mycinema.service.ICinemaService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "电影院管理接口")
@RequestMapping("/cinema")
@RestController
@AllArgsConstructor
public class CinemaController {

    private final ICinemaService cinemaService;

    @GetMapping
    public List<CinemaVO> getAllCinema(){
        return cinemaService.getAllCinema();
    }

}
