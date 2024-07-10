package com.example.mycinema.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@TableName("cinema")
public class Cinema {
    @TableId(type = IdType.AUTO)
    private Long cinemaId;

    private String cinemaName;

    private String cinemaPicture;//the name of the image of the cinema

    private String location;

    private String showingMovieIds; // 上映电影的id列表

    private String ticketPrices; //电影名和电影票价的映射

    public List<Long> getShowingMovieIds() {
        return Arrays.stream(showingMovieIds.split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());
    }
}
