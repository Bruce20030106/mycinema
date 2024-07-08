package com.example.mycinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mycinema.domain.po.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
