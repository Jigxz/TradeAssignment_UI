package com.ts.tradestore.respository;


import com.ts.tradestore.modal.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TradeStoreRepository extends JpaRepository<Trade, String> {
}