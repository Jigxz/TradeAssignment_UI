package com.ts.tradestore.controller;

import com.ts.tradestore.modal.Trade;
import com.ts.tradestore.service.TradeStoreService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import static java.util.Arrays.asList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = TradeStoreController.class)
class TradeStoreControllerTest {

    @MockBean
    private TradeStoreService tradeMockService;

    @Autowired
    private MockMvc mockMvc;


    public static LocalDate getLocalDate(int year, int month, int day) {
        LocalDate localDate = LocalDate.of(year, month, day);
        return localDate;
    }

    @Test
    @DisplayName("get all Trades when calling - /trades")
    void shouldGetAllTrades() throws Exception {


        LocalDate maturityDt = getLocalDate(2015, 05, 21);
        LocalDate createdDate = getLocalDate(2015, 05, 21);

        Trade trade1 = new Trade("T1", 1, "CP-1", "B1", maturityDt, createdDate, "Y");
        Trade trade2 = new Trade("T2", 2, "CP-1", "B1", maturityDt, createdDate, "Y");


        Mockito.when(tradeMockService.findAll()).thenReturn(asList(trade1, trade2));

        mockMvc.perform(get("/trade"))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", Matchers.is(2)))
                .andExpect(jsonPath("$[0].tradeId", Matchers.is("T1")))
                .andExpect(jsonPath("$[0].version", Matchers.is(1)))
                .andExpect(jsonPath("$[1].tradeId", Matchers.is("T2")))
                .andExpect(jsonPath("$[1].version", Matchers.is(2)))
                .andExpect(jsonPath("$[1].maturityDt", Matchers.is(getLocalDate(2015, 05, 21).toString())));
    }

    @Test
    @DisplayName("add trade to store - /trade")
    void shouldAddTradeToStore() throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");

        LocalDate maturityDt = LocalDate.now();
        LocalDate createdDate = LocalDate.now();

        Trade trade = new Trade("T1", 1, "CP-1", "B1", maturityDt, createdDate, "Y");

        ResponseEntity<?> responseEntity = new ResponseEntity<>(
                trade,
                HttpStatus.CREATED
        );

//        Mockito.when(tradeMockService.saveTrade(trade)).thenReturn(void);

        mockMvc.perform(get("/trade"))
                .andExpect(status().is(200));
    }
}