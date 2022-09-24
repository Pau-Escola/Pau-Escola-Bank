package com.ironhack.pauescolabank.services;

import com.ironhack.pauescolabank.DTO.AccountHolderDTO;
import com.ironhack.pauescolabank.embedded.Address;
import com.ironhack.pauescolabank.model.Account;
import com.ironhack.pauescolabank.model.Checking;
import com.ironhack.pauescolabank.model.Credit;
import com.ironhack.pauescolabank.model.Saving;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import com.ironhack.pauescolabank.repositories.AccountHolderRepository;
import com.ironhack.pauescolabank.repositories.CheckingRepository;
import com.ironhack.pauescolabank.repositories.CreditRepository;
import com.ironhack.pauescolabank.repositories.SavingRepository;
import com.ironhack.pauescolabank.requests.TransferRequest;
import com.ironhack.pauescolabank.utilities.TransferChecker;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountHolderService {
    private final AccountHolderRepository accountHolderRepository;
    private final CreditRepository creditRepository;
    private final SavingRepository savingRepository;
    private final CheckingRepository checkingRepository;

    private final TransferChecker transferChecker;


    public AccountHolderService(AccountHolderRepository accountHolderRepository, CreditRepository creditRepository, SavingRepository savingRepository, CheckingRepository checkingRepository) {
        this.accountHolderRepository = accountHolderRepository;
        this.creditRepository = creditRepository;
        this.savingRepository = savingRepository;
        this.checkingRepository = checkingRepository;
        this.transferChecker = new TransferChecker(accountHolderRepository, this);

    }

    //todo shauri de fer que retornes dtos i no entities
    public List<AccountHolder> findAll() {
        return accountHolderRepository.findAll();
    }

    public AccountHolder save(AccountHolderDTO accountHolderDTO) {
        AccountHolder accountHolder = new AccountHolder();
        return accountHolderRepository.save(accountHolder.fromDTO(accountHolderDTO));
    }

    public AccountHolder save(AccountHolder accountHolder) {
        return accountHolderRepository.save(accountHolder);
    }

    public String delete(Long id) {
        accountHolderRepository.delete(
                accountHolderRepository.findById(id).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "There's no Account Holder with id: " + id)));
        return "Account Holder with id: " + id + " has been removed from the database";
    }

    public AccountHolder getById(Long id) {
        return accountHolderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There's no Account Holder with id: " + id));
    }


    public AccountHolderDTO updateAddress(Long id, Address address) {
        var accountHolder = accountHolderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There's no Account Holder with id: " + id));
        accountHolder.setAddress(address);
        var accountHolderDTO = new AccountHolderDTO();
        return accountHolderDTO.fromEntity(accountHolderRepository.save(accountHolder));
    }

    public AccountHolder get(String keycloakId) {
        for (AccountHolder accountHolder : accountHolderRepository.findAll()) {
            if (accountHolder.getKeycloakId().equals(keycloakId)) {
                return accountHolder;
            }

        }
        return null;
    }

    public List<Account> getAccounts(String keycloakId){
        var userTocheck = new AccountHolder();
        for (AccountHolder accountHolder : accountHolderRepository.findAll()) {
            if (accountHolder.getKeycloakId().equals(keycloakId)) {
                userTocheck = accountHolder;
            }

        }
        List <Account> accountsToDisplay = new ArrayList<>();

        for (Checking checking : checkingRepository.findAll()) {
            if (checking.getOwner().getKeycloakId().equals(keycloakId)) {
                accountsToDisplay.add(checking);
            }
        }

        for (Saving saving : savingRepository.findAll()) {
            if (saving.getOwner().getKeycloakId().equals(keycloakId)) {
                accountsToDisplay.add(saving);
            }
        }

        for (Credit credit : creditRepository.findAll()) {
            if (credit.getOwner().getKeycloakId().equals(keycloakId)) {
                accountsToDisplay.add(credit);
            }
        }
        return accountsToDisplay;
    }
//todo shan de fer els metodes to string de account holders i...de tot basicament
    public AccountHolder updateAddress(String keycloakId, Address newAddress) {
        for (AccountHolder accountHolder : accountHolderRepository.findAll()) {
            if (accountHolder.getKeycloakId().equals(keycloakId)) {
                 accountHolder.setAddress(newAddress);
                 return accountHolder;
            }

        }
        return null;
    }

    public String makeTransfer(TransferRequest transferRequest, String keyCloakId) {
        if (transferChecker.assessTransferAvailability(transferRequest, keyCloakId )){
            var reciever = getById(transferRequest.getToAccountOwnerId());
            var accountsToIterate = getAccounts(keyCloakId);
            var accountsToIterate2 = getAccounts(reciever.getKeycloakId());
            for (Account account: accountsToIterate){
                if (transferRequest.getFromAccountId() == account.getId()){
                    account.getBalance().setMoney(account.getBalance().getMoney().subtract(transferRequest.getMoneyToTransfer()));
                }
            }
            for (Account account: accountsToIterate2){
                if(transferRequest.getToAccountId()== account.getId()){
                    account.getBalance().setMoney(account.getBalance().getMoney().add(transferRequest.getMoneyToTransfer()));
                }
            }

            return "The transfer has been correctly done";
        } else return "There was an error";
    }



    /*public AccountHolderDTO addAccount(Long user_id, Long account_id) {
        var accountHolder = accountHolderRepository.findById(user_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There's no Account Holder with id: " + user_id));
        var account = accountRepository.findById(user_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There's no Account Holder with id: " + user_id));
        accountHolder.setBalance(balance);
        CheckingDTO checkingDTO = new CheckingDTO();
        return checkingDTO.fromEntity(checking);
    }*/

    /*public AccountHolder updateAll(Long id, AccountHolder accountHolder) {
        var accountHolderToUpdate = accountHolderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There's no Account Holder with id " + id));
        if (accountHolder.getOwner() != null)
            accountHolderToUpdate.setOwner(accountHolder.getOwner());
        if (accountHolder.getAccountStatus() != null)
            accountHolderToUpdate.setAccountStatus(accountHolder.getAccountStatus());
        if (accountHolder.getBalance() != null)
            accountHolderToUpdate.setBalance(accountHolder.getBalance());
        if (accountHolder.getPenaltyFee() != null)
            accountHolderToUpdate.setPenaltyFee(accountHolder.getPenaltyFee());
        if (accountHolder.getInterestRate() != null)
            accountHolderToUpdate.setInterestRate(accountHolder.getInterestRate());
        if (accountHolder.getMoneyOwed() != null)
            accountHolderToUpdate.setMoneyOwed(accountHolder.getMoneyOwed());
        if (accountHolder.getCreditLimit() != null)
            accountHolderToUpdate.setCreditLimit(accountHolder.getCreditLimit()));

        return accountHolderRepository.save(accountHolderToUpdate);

    }*/
}
