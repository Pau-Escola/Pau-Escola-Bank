package com.ironhack.pauescolabank.embedded;

import javax.persistence.Embeddable;

@Embeddable
public interface Fee {
    public void apply();
}
