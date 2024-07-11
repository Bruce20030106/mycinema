package com.example.mycinema.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mycinema.domain.dto.OrderInfo;
import com.example.mycinema.domain.po.Order;
import com.example.mycinema.domain.vo.OrderVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOrderService extends IService<Order> {
    List<OrderVO> getAllOrdersByUserId(Long userId);

    OrderVO getAOrderByOrderId(Long orderId);

    void createOrder(OrderInfo orderInfo);

    void deleteOrder(Long orderId);
}
