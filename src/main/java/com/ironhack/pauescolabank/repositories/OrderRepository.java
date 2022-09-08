package com.ironhack.pauescolabank.repositories;

import com.ironhack.pauescolabank.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
