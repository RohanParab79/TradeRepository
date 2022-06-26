package com.parasoft.tradeapp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Trade implements Comparable<Trade> {

    @Id
    private String tradeId;
    private Integer version;
    private String counterPartyId;
    private String bookId;
    private LocalDate maturityDate;
    private LocalDate createDate;
    private Character expired;


    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCounterPartyId() {
        return counterPartyId;
    }

    public void setCounterPartyId(String counterPartyId) {
        this.counterPartyId = counterPartyId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public Character getExpired() {
        return expired;
    }

    public void setExpired(Character expired) {
        this.expired = expired;
    }

    public Trade(String tradeId, Integer version, String counterPartyId, String bookId, LocalDate maturityDate, LocalDate createDate, Character expired) {
        this.tradeId = tradeId;
        this.version = version;
        this.counterPartyId = counterPartyId;
        this.bookId = bookId;
        this.maturityDate = maturityDate;
        this.createDate = createDate;
        this.expired = expired;
    }

    public Trade() {
    }

    @Override
    public String toString() {
        return "Trade{" +
                "tradeId=" + tradeId +
                ", version=" + version +
                ", counterPartyId='" + counterPartyId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", maturityDate=" + maturityDate +
                ", createDate=" + createDate +
                ", expired=" + expired +
                '}';
    }

    @Override
    public int compareTo(Trade t) {
        return Integer.parseInt(t.getTradeId().substring(1)) < Integer.parseInt(this.getTradeId().substring(1))? 1:-1;
    }
}