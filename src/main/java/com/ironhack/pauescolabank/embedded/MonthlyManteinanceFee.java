package com.ironhack.pauescolabank.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MonthlyManteinanceFee implements Fee {
    private BigDecimal monthlyMaintenanceFee;
    @Override
    public void apply() {

    }
}
