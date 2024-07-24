package com.xuyan.stock.service;

import com.xuyan.stock.pojo.domain.InnerMarketDomain;
import com.xuyan.stock.pojo.domain.OuterMarketDomain;
import com.xuyan.stock.pojo.domain.StockBlockDomain;
import com.xuyan.stock.vo.resp.R;

import java.util.List;

public interface StockService {
    R<List<InnerMarketDomain>> getInnerMarketInfo();

    R<List<StockBlockDomain>> sectorAllLimit();

    R<List<OuterMarketDomain>> externalIndex();
}
