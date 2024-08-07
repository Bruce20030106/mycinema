package com.example.mycinema.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("Orders")
public class Order {
    @TableId(type = IdType.AUTO)
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

    @TableField("ticket_prices")
    private Integer ticketPrice;

    private Integer orderPrice; //订单金额=电影票数×票单价

}
