package com.ironhack.pauescolabank.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class Money {
    private final String currency = "€";
    private BigDecimal money;
    private LocalDate dateTracker;

    public Money(BigDecimal money) {
        this.money = money;
        this.dateTracker = LocalDate.now();
    }
}
