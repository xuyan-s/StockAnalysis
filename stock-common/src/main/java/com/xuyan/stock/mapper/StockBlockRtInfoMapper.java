package com.xuyan.stock.mapper;

import com.xuyan.stock.pojo.domain.OuterMarketDomain;
import com.xuyan.stock.pojo.domain.StockBlockDomain;
import com.xuyan.stock.pojo.entity.StockBlockRtInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
* @author 14562
* @description 针对表【stock_block_rt_info(股票板块详情信息表)】的数据库操作Mapper
* @createDate 2024-07-20 16:36:55
* @Entity com.xuyan.stock.pojo.entity.StockBlockRtInfo
*/
public interface StockBlockRtInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockBlockRtInfo record);

    int insertSelective(StockBlockRtInfo record);

    StockBlockRtInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockBlockRtInfo record);

    int updateByPrimaryKey(StockBlockRtInfo record);

    List<StockBlockDomain> sectorAllLimit(@Param("timePoint") Date curDate);

    List<OuterMarketDomain> externalIndex(@Param("timePoint") Date lastDate);
}
