package com.ironhack.pauescolabank.utilities;

import com.ironhack.pauescolabank.model.Account;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import com.ironhack.pauescolabank.repositories.AccountHolderRepository;
import com.ironhack.pauescolabank.requests.TransferRequest;
import com.ironhack.pauescolabank.services.AccountHolderService;
import org.apache.catalina.LifecycleState;

import java.math.BigDecimal;


public class TransferChecker {

    private final AccountHolderRepository accountHolderRepository;
    private final AccountHolderService accountHolderService;

    private boolean isTheOwner;
    private boolean hasEnoughMoneyToTransfer;

    private boolean theOwnerMatchesTheAccount;

    private boolean transferAuthorized;


    public TransferChecker(AccountHolderRepository accountHolderRepository, AccountHolderService accountHolderService) {
        this.accountHolderRepository = accountHolderRepository;
        this.accountHolderService = accountHolderService;
    }

    private boolean accountOwnerShipChecker(String keycloakId, Long accountId){
        var accountsToCheck = accountHolderService.getAccounts(keycloakId);
        this.isTheOwner = false;
        for (Account account : accountsToCheck) {
            if (account.getOwner().getKeycloakId().equals(keycloakId)) {
                this.isTheOwner = true;
            }

        }

        return this.isTheOwner;
    }

    private boolean balanceChecker (String keycloakId, Long accountId, BigDecimal moneyTotransfer){
        var accountsToCheck = accountHolderService.getAccounts(keycloakId);
        this.hasEnoughMoneyToTransfer = false;
        for (Account account : accountsToCheck) {
            if (account.getId().equals(accountId)) {

                if(account.getBalance().getMoney().intValue() > moneyTotransfer.intValue())  this.hasEnoughMoneyToTransfer = true;
            }

        }
        return this.hasEnoughMoneyToTransfer;
    }

    private boolean theRecievingAccountmatchesTheOwnerId(Long accountId, Long userId){
        this.theOwnerMatchesTheAccount = false;
        var userToCheck = accountHolderRepository.findById(userId).orElseThrow();
        var accountsToCheck =  accountHolderService.getAccounts(userToCheck.getKeycloakId());
        for (Account account : accountsToCheck) {
            if (account.getId().equals(accountId)) {
            this.theOwnerMatchesTheAccount = true;
            }
        }
        return theOwnerMatchesTheAccount;
    }

    public boolean assessTransferAvailability(TransferRequest transferRequest, String keycloakId){
        this.transferAuthorized = false;
        if(accountOwnerShipChecker(keycloakId, transferRequest.getFromAccountId()) &&
        balanceChecker(keycloakId, transferRequest.getFromAccountId(), transferRequest.getMoneyToTransfer()) &&
        theRecievingAccountmatchesTheOwnerId(transferRequest.getToAccountId(), transferRequest.getToAccountOwnerId())){
            this.transferAuthorized = true;
        }

        return this.transferAuthorized;
    }
}
