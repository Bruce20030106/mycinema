package com.example.mycinema.controller;

import com.example.mycinema.domain.vo.OrderVO;
import com.example.mycinema.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "订单管理接口")
@RequestMapping("/orders")
@RestController
@AllArgsConstructor
public class OrderController {

    private final IOrderService orderService;

    @ApiOperation("根据当前用户id批量查询用户的历史订单")
    @GetMapping("/{UserId}")
    public List<OrderVO> getAllOrdersByUserId(@PathVariable Long UserId){
        return orderService.getAllOrdersByUserId(UserId);
    }


}
