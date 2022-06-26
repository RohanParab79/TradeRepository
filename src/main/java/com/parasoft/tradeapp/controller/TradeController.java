package com.parasoft.tradeapp.controller;

import com.parasoft.tradeapp.service.TradeService;
import com.parasoft.tradeapp.entity.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @PostMapping("/tradeAdd")
    public String saveTrade(@RequestBody Trade trade){

        try {
            tradeService.processTrade(trade);
            return "Successfully Inserted";
        }
        catch (Exception e) {
            return "FAILED: Failure Reason -->" + e.getMessage();
        }
    }

    @GetMapping("/trades")
    public String fetchTradeList(){
            return tradeService.fetchTradeList();
    }

    @PostMapping("/runExpiry")
    public String updateExpiry(){
        return tradeService.runExpiry();
    }

}
