package com.xuyan.stock.pojo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author by xuyan
 * @Date 2022/1/9
 * @Description 定义封装多内大盘数据的实体类
 */
@Data
public class OuterMarketDomain {
    /**
     * 大盘编码
     */
    private String code;
    /**
     * 大盘名称
     */
    private String name;

    /**
     * 当前点
     */
    private BigDecimal curPoint;

    /**
     * 涨跌值
     */
    private BigDecimal upDown;
    /**
     * 涨幅
     */
    private BigDecimal rose;

    /**
     * 当前时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date curTime;
}
