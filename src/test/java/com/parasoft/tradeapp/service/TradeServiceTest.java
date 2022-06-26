package com.parasoft.tradeapp.service;

import com.parasoft.tradeapp.entity.Trade;
import com.parasoft.tradeapp.repository.TradeRepository;
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
class TradeServiceTest {

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
    public void TestCaseForRunExpiry(){
        String s1 = tradeService.runExpiry();
        assertEquals("Expiry update complete",s1);

    }

    @Test
    public void TestCaseForFetchTradeList(){
        String s1 = tradeService.fetchTradeList();
        String htmlReponseExpected = "<h2> TRADE DETAILS</h2><table border=1><tr><th>Trade id</th><th>Version</th><th>Counter Party ID</th><th>Book ID</th><th>Maturity Date</th><th>Create Date</th><th>Expiry</th></tr><tr><td>T1</td><td>1</td><td>BOOK</td><td>CP</td><td>2022-07-27</td><td>2022-07-27</td><td>N</td></tr></table>";
        assertEquals(htmlReponseExpected,s1);

    }

    @Test
    public void TestCaseForInvalidTradeId(){
        try{
            Trade t1 = tradeService.processTrade(new Trade("K1",1,"BOOK","CP", LocalDate.parse("2022-07-27"),LocalDate.parse("2022-07-27"),'N'));
            assertEquals(1,2);
        }
        catch (Exception e){
            assertEquals("Invalid trade idK1",e.getMessage());

        }

    }

    @Test
    public void TestCaseForInvalidMaturityDate(){
        try{
            Trade t1 = tradeService.processTrade(new Trade("T1",1,"BOOK","CP", LocalDate.parse("2021-07-27"),LocalDate.parse("2022-07-27"),'N'));
            assertEquals(1,2);
        }
        catch (Exception e){
            assertEquals("Invalid Maturity date: 2021-07-27",e.getMessage());

        }

    }

    @Test
    public void TestCaseForExistsById(){
        try{
            Boolean b = tradeService.existsById("T1");
            assertTrue(b);
        }
        catch (Exception e){
            assertEquals("Invalid Maturity date: 2021-07-27",e.getMessage());

        }

    }

    @Test
    public void TestCaseForFindById(){
        try{
            Trade t = tradeService.findById("T1");
            assertEquals("T1",t.getTradeId());
        }
        catch (Exception e){
            assertEquals("Invalid Maturity date: 2021-07-27",e.getMessage());

        }

    }

}