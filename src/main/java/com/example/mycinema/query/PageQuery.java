package com.example.mycinema.query;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "用户查询条件实体")
public class PageQuery {
    @ApiModelProperty("页码")
    private Integer pageNo = 1;

    @ApiModelProperty("页面大小")
    private Integer pageSize = 5;

    @ApiModelProperty("排序字段")
    private String sortBy;

    @ApiModelProperty("是否升序")
    private Boolean isAsc = true;

    public <T> Page<T> toMpPage(OrderItem ... items){
        //分页条件
        Page<T> page = Page.of(pageNo, pageSize);

        //排序条件
        if(StrUtil.isNotBlank(sortBy)){
            page.addOrder(new OrderItem(sortBy,isAsc));
        }else if (items != null){
            page.addOrder(items);
        }
        return page;
    }

    public <T> Page<T> toMpPage(String defaultSortBy, Boolean defaultAsc){

        return toMpPage(new OrderItem(defaultSortBy, defaultAsc));
    }

    public <T> Page<T> toMpPageDefaultSortByRate(){

        return toMpPage(new OrderItem("rate",false));
    }

    public <T> Page<T> toMpPageDefaultSortByCreateTime(){

        return toMpPage(new OrderItem("create_time",false));
    }
}
