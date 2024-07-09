package com.example.mycinema.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mycinema.domain.po.Order;
import com.example.mycinema.domain.vo.OrderVO;
import com.example.mycinema.mapper.OrderMapper;
import com.example.mycinema.service.IOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService{
    @Override
    public List<OrderVO> getAllOrdersByUserId(Long userId) {
        List<Order> orderList = lambdaQuery().eq(Order::getUserId, userId).list();

        List<OrderVO> orderVOList = BeanUtil.copyToList(orderList, OrderVO.class);

        return orderVOList;

    }
}
