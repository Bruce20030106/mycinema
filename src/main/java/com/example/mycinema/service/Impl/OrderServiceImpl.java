package com.example.mycinema.service.Impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mycinema.domain.po.Order;
import com.example.mycinema.mapper.OrderMapper;
import com.example.mycinema.service.IOrderService;

public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService{
}
