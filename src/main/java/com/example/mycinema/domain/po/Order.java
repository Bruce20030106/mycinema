package com.example.mycinema.domain.po;

import java.time.LocalDateTime;

public class Order {
    private Long orderId;
    private Long userId;
    private String userName;
    private Long movieId;
    private String movieName;
    private Long cinemaId;
    private String cinemaName;
    private LocalDateTime orderTime; //下单时间
    private String hallNumber; //影厅号，如3D5号厅
    private String seatNumber; //座位号，如F35
    private LocalDateTime showTime;
    private Integer ticketCount; //一次买了几张票
    private Integer ticketPrices;
    private Integer orderPrice; //订单金额=电影票数×票单价

}
