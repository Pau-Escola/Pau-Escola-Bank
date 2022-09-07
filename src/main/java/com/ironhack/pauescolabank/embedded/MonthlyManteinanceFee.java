package com.ironhack.pauescolabank.embedded;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class MonthlyManteinanceFee implements Fee {
    private BigDecimal monthlyMaintenanceFee;
    @Override
    public void apply() {

    }
}
