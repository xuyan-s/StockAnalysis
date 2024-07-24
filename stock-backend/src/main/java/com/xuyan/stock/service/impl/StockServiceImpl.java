package com.xuyan.stock.service.impl;

import com.xuyan.stock.mapper.StockBlockRtInfoMapper;
import com.xuyan.stock.mapper.StockMarketIndexInfoMapper;
import com.xuyan.stock.mapper.StockRtInfoMapper;
import com.xuyan.stock.pojo.domain.InnerMarketDomain;
import com.xuyan.stock.pojo.domain.OuterMarketDomain;
import com.xuyan.stock.pojo.domain.StockBlockDomain;
import com.xuyan.stock.pojo.vo.StockInfoConfig;
import com.xuyan.stock.service.StockService;
import com.xuyan.stock.utils.DateTimeUtil;
import com.xuyan.stock.vo.resp.R;
import com.xuyan.stock.vo.resp.ResponseCode;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockInfoConfig stockInfoConfig;
    @Autowired
    private StockMarketIndexInfoMapper stockMarketIndexInfoMapper;
    @Autowired
    private StockBlockRtInfoMapper stockBlockRtInfoMapper;

    @Override
    public R<List<InnerMarketDomain>> getInnerMarketInfo() {
        //获取股票最新交易时间点
        DateTime curDateTime = DateTimeUtil.getLastDate4Stock(DateTime.now());
        Date curDate = curDateTime.toDate();
        //获取大盘编码集合
        List<String> mCodes = stockInfoConfig.getInner();
        //调用mapper查询数据
        List<InnerMarketDomain> data = stockMarketIndexInfoMapper.getMarketInfo(curDate, mCodes);
        return R.ok(data);
    }

    @Override
    public R<List<StockBlockDomain>> sectorAllLimit() {
        Date curDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();

        curDate=DateTime.parse("2021-12-21 14:30:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();

        List<StockBlockDomain> infos = stockBlockRtInfoMapper.sectorAllLimit(curDate);
        //2.组装数据
        if (CollectionUtils.isEmpty(infos)) {
            return R.error(ResponseCode.NO_RESPONSE_DATA.getMessage());
        }
        return R.ok(infos);

    }

    @Override
    public R<List<OuterMarketDomain>> externalIndex() {
        //获取股票最新交易时间点
        Date lastDate=DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        //TODO mock数据,后续删除
        lastDate=DateTime.parse("2022-05-18 15:58:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        //1.调用mapper接口获取数据
        List<OuterMarketDomain> infos= stockBlockRtInfoMapper.externalIndex(lastDate);
        //4.返回查询结果
        return R.ok(infos);
    }
}
