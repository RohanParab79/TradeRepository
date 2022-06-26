package com.parasoft.tradeapp.service;

import com.parasoft.tradeapp.entity.Trade;
import com.parasoft.tradeapp.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class TradeServiceImpl implements TradeService{

    @Autowired
    private TradeRepository tradeRepository;

    private void validateTrade(Trade trade) throws Exception {

        String tradeId = trade.getTradeId();
        if (!trade.getTradeId().startsWith("T"))
        {
            throw new RuntimeException("Invalid trade id" + trade.getTradeId());
        }
        if (trade.getMaturityDate().isBefore(LocalDate.now())){
            throw new RuntimeException("Invalid Maturity date: "  + trade.getMaturityDate());
        }
        if (existsById(tradeId)){
            Trade tradeDb = findById(tradeId);
            if (trade.getVersion() < tradeDb.getVersion())
            {
                throw new RuntimeException("Invalid Version: "  + trade.getVersion());
            }
        }

    }

    @Override
    public Trade processTrade(Trade trade) throws Exception{
        validateTrade(trade);
        return tradeRepository.save(trade);
    }

    @Override
    public Boolean existsById (String tradeId){
        return tradeRepository.existsById(tradeId);
    }

    @Override
    public Trade findById (String tradeId){
         return tradeRepository.findById(tradeId).get();
    }

    @Override
    public String fetchTradeList () {
        List<Trade> tradelist = tradeRepository.findAll();
        Collections.sort(tradelist);
        StringBuilder sb = new StringBuilder();
        sb.append("<h2> TRADE DETAILS</h2>");
        sb.append("<table border=1>");
        sb.append("<tr>");
        sb.append("<th>Trade id</th>");
        sb.append("<th>Version</th>");
        sb.append("<th>Counter Party ID</th>");
        sb.append("<th>Book ID</th>");
        sb.append("<th>Maturity Date</th>");
        sb.append("<th>Create Date</th>");
        sb.append("<th>Expiry</th>");
        sb.append("</tr>");

        for (Trade t: tradelist) {

            sb.append("<tr>");
            sb.append("<td>");
            sb.append(t.getTradeId());
            sb.append("</td>");
            sb.append("<td>");
            sb.append(t.getVersion());
            sb.append("</td>");
            sb.append("<td>");
            sb.append(t.getCounterPartyId());
            sb.append("</td>");
            sb.append("<td>");
            sb.append(t.getBookId());
            sb.append("</td>");
            sb.append("<td>");
            sb.append(t.getMaturityDate());
            sb.append("</td>");
            sb.append("<td>");
            sb.append(t.getCreateDate());
            sb.append("</td>");
            sb.append("<td>");
            sb.append(t.getExpired());
            sb.append("</td>");
            sb.append("</tr>");

        }

        sb.append("</table>");
        return sb.toString();
    }

    @Override
    public String runExpiry () {
        List<Trade> tradelist = tradeRepository.findAll();
        for (Trade t: tradelist) {
            if(t.getMaturityDate().isBefore(LocalDate.now()))
                t.setExpired('Y');
            else t.setExpired('N');

            tradeRepository.save(t);
        }
        return "Expiry update complete";
    }


}
