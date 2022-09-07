package com.ironhack.pauescolabank.embedded;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class PenaltyFee implements Fee {
    private BigDecimal penaltyFee;

    @Override
    public void apply() {

    }
}
