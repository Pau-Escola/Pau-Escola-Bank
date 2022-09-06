package com.ironhack.pauescolabank.embedded;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String country;
    private String city;
    private Integer zipCode;
    private String street;
}
