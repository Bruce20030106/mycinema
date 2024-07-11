package com.example.mycinema.controller;

import com.example.mycinema.common.R;
import com.example.mycinema.domain.dto.OrderInfo;
import com.example.mycinema.domain.vo.OrderVO;
import com.example.mycinema.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "订单管理接口")
@RestController
@AllArgsConstructor
public class OrderController {

    private final IOrderService orderService;

    @ApiOperation("根据当前用户id批量查询用户的历史订单")
    @GetMapping("/{UserId}")
    public R<List<OrderVO>> getAllOrdersByUserId(@PathVariable Long UserId){
        List<OrderVO> orders = orderService.getAllOrdersByUserId(UserId);
        return R.success(orders);
    }

    @ApiOperation("根据订单Id查询订单详情")
    @GetMapping("/api/orders/{orderId}")
    public R<OrderVO> getOrderByOrderId(@PathVariable Long orderId){
        OrderVO orderVO = orderService.getAOrderByOrderId(orderId);
        return R.success(orderVO);
    }

    @ApiOperation("创建订单")
    @GetMapping("/orders")
    public R<String> createOrder(@RequestBody OrderInfo orderInfo){
        orderService.createOrder(orderInfo);
        return R.success("订单创建成功！");
    }

    @ApiOperation("删除订单")
    @DeleteMapping("/orders/{orderId}")
    public R<String> createOrder(@PathVariable Long orderId){
        orderService.deleteOrder(orderId);
        return R.success("订单删除成功！");
    }

}
