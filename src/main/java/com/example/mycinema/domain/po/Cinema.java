package com.example.mycinema.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Data
public class Cinema {
    @TableId(type = IdType.AUTO)
    private Long cinemaId;

    private String cinemaName;

    private String location;

    private List<Long> showingMovieIds; // 上映电影的id列表

    private Map<String,Integer> ticketPrices; //电影名和电影票价的映射


}
