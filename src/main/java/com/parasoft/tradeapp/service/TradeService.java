package com.parasoft.tradeapp.service;

import com.parasoft.tradeapp.entity.Trade;

public interface TradeService {
    public Trade processTrade(Trade trade) throws Exception;

    public String fetchTradeList();

    public String runExpiry();

    public Boolean existsById(String tradeId);

    public Trade findById(String tradeId);
}
