package com.example.mycinema.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mycinema.common.DateTimeUtil;
import com.example.mycinema.common.UUIDUtil;
import com.example.mycinema.domain.dto.OrderInfo;
import com.example.mycinema.domain.po.Order;
import com.example.mycinema.domain.vo.OrderVO;
import com.example.mycinema.mapper.CinemaMapper;
import com.example.mycinema.mapper.MovieMapper;
import com.example.mycinema.mapper.OrderMapper;
import com.example.mycinema.mapper.UserMapper;
import com.example.mycinema.service.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService{

    private final OrderMapper orderMapper;

    private final UserMapper userMapper;

    private final CinemaMapper cinemaMapper;

    private MovieMapper movieMapper;

    @Override
    public List<OrderVO> getAllOrdersByUserId(Long userId) {

        List<Order> list = lambdaQuery().eq(Order::getUserId, userId).list();

        List<OrderVO> orderVOList = list.stream()
                .map(order -> BeanUtil.copyProperties(order, OrderVO.class)).toList();

        return orderVOList;

    }

    @Override
    public OrderVO getAOrderByOrderId(Long orderId) {
        Order order = orderMapper.selectById(orderId);

        return BeanUtil.copyProperties(order,OrderVO.class);
    }

    @Override
    public void createOrder(OrderInfo orderInfo) {
        Order order = BeanUtil.copyProperties(orderInfo, Order.class);

        order.setUserName(userMapper.selectById(orderInfo.getUserId()).getUserName());

        order.setMovieName(movieMapper.selectById(orderInfo.getMovieId()).getMovieName());

        order.setCinemaName(cinemaMapper.selectById(orderInfo.getCinemaId()).getCinemaName());

        order.setOrderId(UUIDUtil.generateUUIDAsLong());

        order.setOrderPrice(orderInfo.getTicketPrice()*orderInfo.getTicketCount());

        order.setOrderTime(DateTimeUtil.getCurrentDateTime());

        orderMapper.insert(order);

    }

    @Override
    public void deleteOrder(Long orderId) {
        orderMapper.deleteById(orderId);
    }
}
