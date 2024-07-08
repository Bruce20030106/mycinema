package com.example.mycinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mycinema.domain.po.Cinema;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CinemaMapper extends BaseMapper<Cinema> {
}
