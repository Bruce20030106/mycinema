package com.example.mycinema.domain.dto;

import lombok.Data;

@Data
public class OrderInfo {

    private  String userId;

    private  String movieId;

    private  String cinemaId;

    private  String hallNumber;

    private  String seatNumber;

    private  String showTime;

    private Integer ticketCount;

    private Integer ticketPrice;

}
