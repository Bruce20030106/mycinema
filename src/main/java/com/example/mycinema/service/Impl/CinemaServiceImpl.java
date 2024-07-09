package com.example.mycinema.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mycinema.domain.po.Cinema;
import com.example.mycinema.domain.vo.CinemaVO;
import com.example.mycinema.mapper.CinemaMapper;
import com.example.mycinema.service.ICinemaService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.parsing.BeanEntry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CinemaServiceImpl extends ServiceImpl<CinemaMapper, Cinema> implements ICinemaService{

    private final CinemaMapper cinemaMapper;

    @Override
    public List<CinemaVO> getAllCinema() {
        List<Cinema> cinemas = cinemaMapper.selectList(null);

        List<CinemaVO> cinemaVOS = BeanUtil.copyToList(cinemas, CinemaVO.class);

        return cinemaVOS;

    }

}
