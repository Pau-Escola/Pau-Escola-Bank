package com.ironhack.pauescolabank.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.pauescolabank.DTO.CreditDTO;
import com.ironhack.pauescolabank.DTO.SavingDTO;
import com.ironhack.pauescolabank.embedded.Address;
import com.ironhack.pauescolabank.embedded.Money;
import com.ironhack.pauescolabank.enums.AccountStatus;
import com.ironhack.pauescolabank.model.Account;
import com.ironhack.pauescolabank.model.Credit;
import com.ironhack.pauescolabank.model.Saving;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import com.ironhack.pauescolabank.repositories.AccountHolderRepository;
import com.ironhack.pauescolabank.repositories.CreditRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CreditControllerTest {
    WebApplicationContext webApplicationContext;

    CreditRepository creditRepository;

    AccountHolderRepository accountHolderRepository;

    private Account account;
    private AccountHolder accountHolder;
    private MockMvc mockMvc;


    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public CreditControllerTest(
            WebApplicationContext webApplicationContext,
            CreditRepository creditRepository,
            AccountHolderRepository accountHolderRepository) {
        this.webApplicationContext = webApplicationContext;
        this.creditRepository = creditRepository;
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
                addressTest, "test@gmail.com", null);;
        accountHolder = accountHolderRepository.save(accountHolderTest);
        var money = new Money("€", BigDecimal.valueOf(700));
        var creditToTest = new Credit();
        creditToTest.setSecretKey("ES7521004586");
        creditToTest.setAccountStatus(AccountStatus.ACTIVE);
        creditToTest.setBalance(money);
        creditToTest.setOwner(accountHolderTest);
        creditToTest.setInterestRate(0.1);
        account=creditRepository.save(creditToTest);


    }

    @AfterEach
    void tearDown() {
        creditRepository.deleteAll();

    }

    @Test
    void getAll() throws Exception {
        var result = mockMvc
                .perform(get("/api/v1/accounts/credits"))
                .andExpect(status().isOk()) // check status code 200
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("ES7521004586"));
    }

    @Test
    void getById() throws Exception {
        var result = mockMvc
                .perform(get("/api/v1/accounts/credits/{id}" , account.getId()))
                .andExpect(status().isOk()) // check status code 200
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("ES7521004586"));
    }

    @Test
    void create() throws Exception {
        var money = new Money("€", BigDecimal.valueOf(700));
        var creditToTest2 = new CreditDTO(
                "ES75210046", AccountStatus.ACTIVE, money, null, 0.2, BigDecimal.valueOf(700), BigDecimal.valueOf(100) );

        var result = mockMvc
                .perform(post("/api/v1/accounts/credits/{id}", accountHolder.getId())
                        .content(asJsonString(creditToTest2))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // check status code 200
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("ES75210046"));
    }

    @Test
    void delete() throws Exception {
        var result = mockMvc
                .perform(MockMvcRequestBuilders.delete("/api/v1/accounts/credits/{id}" , account.getId()))
                .andExpect(status().isOk()) // check status code 200
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("removed"));
    }

    @Test
    void updateAll() throws Exception {
        var money = new Money("€", BigDecimal.valueOf(700));
        var creditToTest2 = new Credit();
        creditToTest2.setSecretKey("ES210046");
        creditToTest2.setAccountStatus(AccountStatus.FROZEN);
        creditToTest2.setBalance(money);

        var result = mockMvc
                .perform(put("/api/v1/accounts/credits/edit_whole/{id}", account.getId())
                        .content(asJsonString(creditToTest2))
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
                .perform(patch("/api/v1/accounts/credits/update_status/{id}", account.getId())
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
                .perform(patch("/api/v1/accounts/credits/update_balance/{id}", account.getId())
                        .content(asJsonString(balanceTotest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // check status code 200
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("45000"));
    }

    @Test
    void shouldOnlyAcceptCreditLimitBetween100And100000(){

        var money = new Money("€", BigDecimal.valueOf(700));
        var creditToTest3 = new CreditDTO(
                "ES75210046", AccountStatus.ACTIVE, money, null, 0.2, BigDecimal.valueOf(700), BigDecimal.valueOf(1000000) );

        assertThrows(Exception.class, ()-> mockMvc
                .perform(post("/api/v1/accounts/credits/{id}", accountHolder.getId())
                        .content(asJsonString(creditToTest3))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn());


    }

    @Test
    void shouldOnlyAcceptInterestRateBetween0_1And0_2() {

        var money = new Money("€", BigDecimal.valueOf(700));
        var creditToTest3 = new CreditDTO(
                "ES75210046", AccountStatus.ACTIVE, money, null, 0.05, BigDecimal.valueOf(700), BigDecimal.valueOf(10000) );

        assertThrows(Exception.class, ()-> mockMvc
                .perform(post("/api/v1/accounts/credits/{id}", accountHolder.getId())
                        .content(asJsonString(creditToTest3))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn());


    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}