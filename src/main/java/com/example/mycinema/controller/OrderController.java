package com.example.mycinema.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "订单管理接口")
@RequestMapping("/orders")
@RestController
public class OrderController {
}
