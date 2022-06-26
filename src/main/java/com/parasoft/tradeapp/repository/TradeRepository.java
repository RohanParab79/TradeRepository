package com.parasoft.tradeapp.repository;

import com.parasoft.tradeapp.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<Trade,String> {
}
