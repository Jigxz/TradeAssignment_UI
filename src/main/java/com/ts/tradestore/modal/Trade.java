package com.ts.tradestore.modal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;


@Entity
@Table(name = "Trades")
public class Trade {

    @Id
    private String tradeId;

    private int version;

    private String counterpartyId;

    private String bookId;

    private LocalDate maturityDt;

    private LocalDate createdDt;

    private String expired;

    public Trade() {

    }

    public Trade(String tradeId, int version, String counterpartyId, String bookId, LocalDate maturityDt, LocalDate createdDt, String expired) {
        this.tradeId = tradeId;
        this.version = version;
        this.counterpartyId = counterpartyId;
        this.bookId = bookId;
        this.maturityDt = maturityDt;
        this.createdDt = createdDt;
        this.expired = expired;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getCounterpartyId() {
        return counterpartyId;
    }

    public void setCounterpartyId(String counterpartyId) {
        this.counterpartyId = counterpartyId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public LocalDate getMaturityDt() {
        return maturityDt;
    }

    public void setMaturityDt(LocalDate maturityDt) {
        this.maturityDt = maturityDt;
    }

    public LocalDate getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(LocalDate createdDt) {
        this.createdDt = createdDt;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    @Override
    public String toString() {
        return "Trades{" +
                "tradeId='" + tradeId + '\'' +
                ", version='" + version + '\'' +
                ", counterpartyId='" + counterpartyId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", maturityDt=" + maturityDt +
                ", createdDt=" + createdDt +
                ", expired='" + expired + '\'' +
                '}';
    }
}
