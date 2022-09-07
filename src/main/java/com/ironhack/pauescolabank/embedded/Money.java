package com.ironhack.pauescolabank.embedded;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class Money {
    private String currency;
    private BigDecimal money;
}
