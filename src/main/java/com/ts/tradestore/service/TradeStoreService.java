package com.ts.tradestore.service;

import com.ts.tradestore.modal.Trade;
import com.ts.tradestore.respository.TradeStoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TradeStoreService {

    private static final Logger log = LoggerFactory.getLogger(TradeStoreService.class);

    @Autowired
    TradeStoreRepository tradeStoreRepository;

    public List<Trade> findAll() {
        return tradeStoreRepository.findAll();
    }

    public boolean doTradeValidation(Trade trade) {
        if (checkMaturityDate(trade)) {
            Optional<Trade> exsitingTrade = tradeStoreRepository.findById(trade.getTradeId());
            if (exsitingTrade.isPresent()) {
                return checkTradeVersion(trade, exsitingTrade.get());
            } else {
                return true;
            }
        }
        return false;
    }

    public void saveTrade(Trade trade) {
        trade.setCreatedDt(LocalDate.now());
        tradeStoreRepository.save(trade);
    }

    //check for trade version is it latest or older
    private boolean checkTradeVersion(Trade trade, Trade oldTrade) {
        if (trade.getVersion() >= oldTrade.getVersion()) {
            return true;
        }
        return false;
    }

    //check maturity date is it before or after
    private boolean checkMaturityDate(Trade trade) {
        return trade.getMaturityDt().isBefore(LocalDate.now()) ? false : true;
    }

    //Store should not allow the trade which has less maturity date then today date.
    public void updateExpiryFlag() {
        tradeStoreRepository.findAll().stream().forEach(t -> {
            if (!checkMaturityDate(t)) {
                t.setExpired("Y");
                log.info("Trade expiry date updated {}", t);
                tradeStoreRepository.save(t);
            }
        });
    }

}
