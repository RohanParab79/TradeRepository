package com.parasoft.tradeapp.controller;

import com.parasoft.tradeapp.entity.Trade;
import com.parasoft.tradeapp.repository.TradeRepository;
import com.parasoft.tradeapp.service.TradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TradeControllerTest {

    @Autowired
    private TradeController tradeController;

    @Autowired
    private TradeService tradeService;
    @MockBean
    private TradeRepository tradeRepository;

    @BeforeEach
    void setUp() {
        List<Trade> list = new ArrayList<>();
        list.add(new Trade("T1",1,"BOOK","CP", LocalDate.parse("2022-07-27"),LocalDate.parse("2022-07-27"),'N'));
        Mockito.when(tradeRepository.findAll()).thenReturn(list);
        Mockito.when(tradeRepository.save(new Trade("T1",10,"BOOK","CP", LocalDate.parse("2022-07-27"),LocalDate.parse("2022-07-27"),'N'))).thenReturn(new Trade("T1",10,"BOOK","CP", LocalDate.parse("2022-07-27"),LocalDate.parse("2022-07-27"),'N'));
        Mockito.when(tradeRepository.existsById("T1")).thenReturn(true);
        Trade MockTrade = new Trade("T1",1,"BOOK","CP", LocalDate.parse("2022-07-27"),LocalDate.parse("2022-07-27"),'N');
        Mockito.when(tradeRepository.findById("T1")).thenReturn(Optional.of(MockTrade));
    }

    @Test
    void saveTradeTest() {
        Trade t1 = new Trade("T1",10,"BOOK","CP", LocalDate.parse("2022-07-27"),LocalDate.parse("2022-07-27"),'N');
        tradeController.saveTrade(t1);
        assertEquals("Successfully Inserted",tradeController.saveTrade(t1));

    }

    @Test
    void saveTradeFailTest() {
        Trade t1 = new Trade("K1",10,"BOOK","CP", LocalDate.parse("2022-07-27"),LocalDate.parse("2022-07-27"),'N');
        tradeController.saveTrade(t1);
        assertEquals("FAILED: Failure Reason -->Invalid trade idK1",tradeController.saveTrade(t1));

    }

    @Test
    void fetchTradeListTest() {
        String htmlReponseExpected = "<h2> TRADE DETAILS</h2><table border=1><tr><th>Trade id</th><th>Version</th><th>Counter Party ID</th><th>Book ID</th><th>Maturity Date</th><th>Create Date</th><th>Expiry</th></tr><tr><td>T1</td><td>1</td><td>BOOK</td><td>CP</td><td>2022-07-27</td><td>2022-07-27</td><td>N</td></tr></table>";
        assertEquals(htmlReponseExpected,tradeController.fetchTradeList());
    }

    @Test
    void updateExpiryTest() {
        assertEquals("Expiry update complete",tradeController.updateExpiry());
    }
}