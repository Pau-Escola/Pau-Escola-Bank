package com.ironhack.pauescolabank.repositories;

import com.ironhack.pauescolabank.model.Order;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {
}
