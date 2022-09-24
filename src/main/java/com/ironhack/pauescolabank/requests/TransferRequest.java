package com.ironhack.pauescolabank.requests;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransferRequest {
    private BigDecimal moneyToTransfer;
    private Long fromAccountId;
    private Long toAccountId;
    private Long toAccountOwnerId;

}
