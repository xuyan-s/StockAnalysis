package com.xuyan.stock.controller;

import com.xuyan.stock.pojo.domain.InnerMarketDomain;
import com.xuyan.stock.pojo.domain.OuterMarketDomain;
import com.xuyan.stock.pojo.domain.StockBlockDomain;
import com.xuyan.stock.service.StockService;
import com.xuyan.stock.vo.resp.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/quot")
public class StockController {
    @Autowired
    private StockService stockService;
    @GetMapping("/index/all")
    public R<List<InnerMarketDomain>> getInnerMarketInfo(){
        return stockService.getInnerMarketInfo();
    }

    @GetMapping("/sector/all")
    public R<List<StockBlockDomain>> sectorAll(){
        return stockService.sectorAllLimit();
    }

    @GetMapping("/external/index")
    public R<List<OuterMarketDomain>> externalIndex(){
        return stockService.externalIndex();
    }

}
