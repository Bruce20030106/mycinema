package com.example.mycinema.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "电影院管理接口")
@RequestMapping("/cinema")
@RestController
public class CinemaController {
}
