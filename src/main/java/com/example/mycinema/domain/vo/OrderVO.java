package com.example.mycinema.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class OrderVO {

    private Long orderId;

    private Long userId;

    private String userName;

    private Long movieId;

    private String movieName;

    private Long cinemaId;

    private String cinemaName;

    private String orderTime; //下单时间

    private String hallNumber; //影厅号，如3D5号厅

    private String seatNumber; //座位号，如F35

    private String showTime;

    private Integer ticketCount; //一次买了几张票

    private Integer ticketPrice;

    private Integer orderPrice; //orderPrice= ticketCount * ticketPrices

}