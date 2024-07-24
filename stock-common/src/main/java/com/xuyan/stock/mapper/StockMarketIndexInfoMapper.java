package com.xuyan.stock.mapper;

import com.xuyan.stock.pojo.domain.InnerMarketDomain;
import com.xuyan.stock.pojo.entity.StockMarketIndexInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
* @author 14562
* @description 针对表【stock_market_index_info(股票大盘数据详情表)】的数据库操作Mapper
* @createDate 2024-07-20 16:36:55
* @Entity com.xuyan.stock.pojo.entity.StockMarketIndexInfo
*/
public interface StockMarketIndexInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockMarketIndexInfo record);

    int insertSelective(StockMarketIndexInfo record);

    StockMarketIndexInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockMarketIndexInfo record);

    int updateByPrimaryKey(StockMarketIndexInfo record);

    List<InnerMarketDomain> getMarketInfo(@Param("curDate") Date curDate, @Param("marketCodes") List<String> marketCodes);
}
