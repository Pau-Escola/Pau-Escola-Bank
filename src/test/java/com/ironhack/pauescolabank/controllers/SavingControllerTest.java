package com.ironhack.pauescolabank.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.pauescolabank.DTO.SavingDTO;
import com.ironhack.pauescolabank.embedded.Address;
import com.ironhack.pauescolabank.embedded.Money;
import com.ironhack.pauescolabank.enums.AccountStatus;
import com.ironhack.pauescolabank.model.Account;
import com.ironhack.pauescolabank.model.Saving;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import com.ironhack.pauescolabank.repositories.AccountHolderRepository;
import com.ironhack.pauescolabank.repositories.SavingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SavingControllerTest {

    WebApplicationContext webApplicationContext;

    SavingRepository savingRepository;

    AccountHolderRepository accountHolderRepository;
    private MockMvc mockMvc;
    private Account account;
    private AccountHolder accountHolder;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public SavingControllerTest(
            WebApplicationContext webApplicationContext,
            SavingRepository savingRepository,
            AccountHolderRepository accountHolderRepository) {
        this.webApplicationContext = webApplicationContext;
        this.savingRepository = savingRepository;
        this.accountHolderRepository = accountHolderRepository;

    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
        var addressTest = new Address("Spain", "Reus", 43204, "Raval de Robuster");
        var accountHolderTest = new AccountHolder("Paulo", "Ew4X5rT",
                LocalDate.of(2000,03,23),
                addressTest, "test@gmail.com", null);
        accountHolder = accountHolderRepository.save(accountHolderTest);
        var money = new Money("€", BigDecimal.valueOf(700));
        var savingToTest = new Saving();
        savingToTest.setSecretKey("ES7521004586");
        savingToTest.setAccountStatus(AccountStatus.ACTIVE);
        savingToTest.setBalance(money);
        savingToTest.setOwner(accountHolderTest);
        savingToTest.setInterestRate(4.5);
        account = savingRepository.save(savingToTest);


    }

    @AfterEach
    void tearDown() {
        savingRepository.deleteAll();

    }

    @Test
    void getAll() throws Exception {
        var result = mockMvc
                .perform(get("/api/v1/accounts/savings"))
                .andExpect(status().isOk()) // check status code 200
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("ES7521004586"));
    }

    @Test
    void getById() throws Exception {
        var result = mockMvc
                .perform(get("/api/v1/accounts/savings/{id}" , account.getId()))
                .andExpect(status().isOk()) // check status code 200
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("ES7521004586"));
    }

    @Test
    void create() throws Exception {
        var money = new Money("€", BigDecimal.valueOf(700));
        var savingToTest2 = new SavingDTO(
                "ES75210046", AccountStatus.ACTIVE, money, null, 4.0, BigDecimal.valueOf(700) );

        var result = mockMvc
                .perform(post("/api/v1/accounts/savings/{id}", accountHolder.getId())
                        .content(asJsonString(savingToTest2))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // check status code 200
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("ES75210046"));
    }

    @Test
    void delete() throws Exception {
        var result = mockMvc
                .perform(MockMvcRequestBuilders.delete("/api/v1/accounts/savings/{id}" , account.getId()))
                .andExpect(status().isOk()) // check status code 200
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("removed"));
    }

    @Test
    void updateAll() throws Exception {
        var money = new Money("€", BigDecimal.valueOf(700));
        var savingToTest2 = new Saving();
        savingToTest2.setSecretKey("ES210046");
        savingToTest2.setAccountStatus(AccountStatus.FROZEN);
        savingToTest2.setBalance(money);

        var result = mockMvc
                .perform(put("/api/v1/accounts/savings/edit_whole/{id}", account.getId())
                        .content(asJsonString(savingToTest2))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // check status code 200
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("FROZEN"));
    }

    @Test
    void updateStatus() throws Exception {
        var statusTotest = AccountStatus.FROZEN;
        var result = mockMvc
                .perform(patch("/api/v1/accounts/savings/update_status/{id}", account.getId())
                        .content(asJsonString(statusTotest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // check status code 200
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("FROZEN"));
    }

    @Test
    void updateBalance() throws Exception {
        var balanceTotest = new Money("$",BigDecimal.valueOf(45000));
        var result = mockMvc
                .perform(patch("/api/v1/accounts/savings/update_balance/{id}", account.getId())
                        .content(asJsonString(balanceTotest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // check status code 200
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("45000"));
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}