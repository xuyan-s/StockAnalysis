package com.xuyan.stock.pojo.vo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 定义股票相关的值对象封装
 */
@ConfigurationProperties(prefix = "stock")
@Data
public class StockInfoConfig {
    //A股大盘ID集合
    private List<String> inner;
    //外盘ID集合
    private List<String> outer;

}
