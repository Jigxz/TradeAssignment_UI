package com.ts.tradestore.controller;

import com.ts.tradestore.exception.InvalidTradeException;
import com.ts.tradestore.modal.Trade;
import com.ts.tradestore.service.TradeStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TradeStoreController {

    @Autowired
    TradeStoreService tradeSrv;

//    @GetMapping("/trade")
    @RequestMapping(value = "/trade", method = RequestMethod.GET)
    public List<Trade> getAllTrades() {
        return tradeSrv.findAll();
    }

//    @PostMapping("/trade")
    @RequestMapping(value = "/trade", method = RequestMethod.POST)
    public ResponseEntity<String> tradeValidate(@RequestBody Trade trade) throws InvalidTradeException {

        if (tradeSrv.doTradeValidation(trade)) {
            tradeSrv.saveTrade(trade);
        } else {
            throw new InvalidTradeException(trade.getTradeId() + "  Trade Id is not found");
        }


        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
