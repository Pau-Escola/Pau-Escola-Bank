package com.ironhack.pauescolabank.repositories;

import com.ironhack.pauescolabank.model.Credit;
import com.ironhack.pauescolabank.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
}
