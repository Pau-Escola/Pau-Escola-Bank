package com.ironhack.pauescolabank.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public final class MonthlyManteinanceFee implements Fee {
    private final BigDecimal monthlyMaintenanceFee = BigDecimal.valueOf(12);
    @Override
    public void apply() {

    }
}
