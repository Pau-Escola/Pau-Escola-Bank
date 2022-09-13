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
public final class PenaltyFee implements Fee {
    private final BigDecimal penaltyFee = BigDecimal.valueOf(40);

    @Override
    public void apply() {

    }
}
