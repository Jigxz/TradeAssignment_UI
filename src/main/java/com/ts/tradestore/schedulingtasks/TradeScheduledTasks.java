package com.ts.tradestore.schedulingtasks;

import com.ts.tradestore.service.TradeStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TradeScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(TradeScheduledTasks.class);

    @Autowired
    TradeStoreService tradeSrv;

    @Scheduled(cron = "${cron.expression}")//30 min
    public void reportCurrentTime() {
        log.info("The time is now {}", LocalDate.now());
        tradeSrv.updateExpiryFlag();
    }
}
