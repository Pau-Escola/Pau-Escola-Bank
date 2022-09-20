package com.ironhack.pauescolabank.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.pauescolabank.DTO.CheckingDTO;
import com.ironhack.pauescolabank.embedded.Address;
import com.ironhack.pauescolabank.embedded.Money;
import com.ironhack.pauescolabank.enums.AccountStatus;
import com.ironhack.pauescolabank.model.Account;
import com.ironhack.pauescolabank.model.Checking;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import com.ironhack.pauescolabank.repositories.AccountHolderRepository;
import com.ironhack.pauescolabank.repositories.CheckingRepository;
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

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CheckingControllerTest {

    WebApplicationContext webApplicationContext;

    CheckingRepository checkingRepository;

    AccountHolderRepository accountHolderRepository;
    private MockMvc mockMvc;
    private Account account;
    private AccountHolder accountHolder;


    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public CheckingControllerTest(
            WebApplicationContext webApplicationContext,
            CheckingRepository checkingRepository,
            AccountHolderRepository accountHolderRepository) {
        this.webApplicationContext = webApplicationContext;
        this.checkingRepository = checkingRepository;
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
        var checkingToTest = new Checking();
        checkingToTest.setSecretKey("ES7521004586");
        checkingToTest.setAccountStatus(AccountStatus.ACTIVE);
        checkingToTest.setBalance(BigDecimal.valueOf(700));
        checkingToTest.setOwner(accountHolderTest);
        account = checkingRepository.save(checkingToTest);


    }

    @AfterEach
    void tearDown() {
        checkingRepository.deleteAll();

    }

    @Test
    void getAll() throws Exception {
        var result = mockMvc
                .perform(get("/api/v1/accounts/checkings"))
                .andExpect(status().isOk()) // check status code 200
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("ES7521004586"));
    }

    @Test
    void getById() throws Exception {
        var result = mockMvc
                .perform(get("/api/v1/accounts/checkings/{id}" , account.getId()))
                .andExpect(status().isOk()) // check status code 200
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("ES7521004586"));
    }

    @Test
    void create() throws Exception {

        var checkingToTest2 = new CheckingDTO(
                "ES75210046", AccountStatus.ACTIVE, BigDecimal.valueOf(700), null, null, BigDecimal.valueOf(700) );

        var result = mockMvc
                .perform(post("/api/v1/accounts/checkings/{id}", accountHolder.getId())
                        .content(asJsonString(checkingToTest2))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // check status code 200
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("ES75210046"));
    }

    @Test
    void delete() throws Exception {
        var result = mockMvc
                .perform(MockMvcRequestBuilders.delete("/api/v1/accounts/checkings/{id}" , account.getId()))
                .andExpect(status().isOk()) // check status code 200
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("removed"));
    }

    //todo lupdateall sha desborrar


    @Test
    void updateStatus() throws Exception {
        var statusTotest = AccountStatus.FROZEN;
        var result = mockMvc
                .perform(patch("/api/v1/accounts/checkings/update_status/{id}", account.getId())
                        .content(asJsonString(statusTotest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // check status code 200
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("FROZEN"));
    }

    @Test
    void updateBalance() throws Exception {
        var balanceTotest = BigDecimal.valueOf(45000);
        var result = mockMvc
                .perform(patch("/api/v1/accounts/checkings/update_balance/{id}", account.getId())
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