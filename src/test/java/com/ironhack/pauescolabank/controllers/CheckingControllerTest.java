package com.ironhack.pauescolabank.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.pauescolabank.DTO.CheckingDTO;
import com.ironhack.pauescolabank.embedded.Address;
import com.ironhack.pauescolabank.embedded.Money;
import com.ironhack.pauescolabank.enums.AccountStatus;
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
        var accountHolderTest = new AccountHolder(LocalDate.of(1997,03,23),addressTest, "test@gmail.com",95.2, null);
        accountHolderTest.setName("Paulo");
        accountHolderRepository.save(accountHolderTest);
        var money = new Money("€", BigDecimal.valueOf(700));
        var checkingToTest = new Checking();
        checkingToTest.setSecretKey("ES7521004586");
        checkingToTest.setAccountStatus(AccountStatus.ACTIVE);
        checkingToTest.setBalance(money);
        checkingToTest.setOwner(accountHolderTest);
        checkingRepository.save(checkingToTest);


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
                .perform(get("/api/v1/accounts/checkings/{id}" , 2))
                .andExpect(status().isOk()) // check status code 200
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("ES7521004586"));
    }

    @Test
    void create() throws Exception {
        var money = new Money("€", BigDecimal.valueOf(700));
        var checkingToTest2 = new CheckingDTO(
                "ES75210046", AccountStatus.ACTIVE, money, null, null, BigDecimal.valueOf(700) );

        var result = mockMvc
                .perform(post("/api/v1/accounts/checkings/{id}", 1L)
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
                .perform(MockMvcRequestBuilders.delete("/api/v1/accounts/checkings/{id}" , 2))
                .andExpect(status().isOk()) // check status code 200
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("removed"));
    }

    @Test
    void updateAll() throws Exception {
        var money = new Money("€", BigDecimal.valueOf(700));
        var checkingToTest2 = new Checking();
        checkingToTest2.setSecretKey("ES210046");
        checkingToTest2.setAccountStatus(AccountStatus.FROZEN);
        checkingToTest2.setBalance(money);

        var result = mockMvc
                .perform(put("/api/v1/accounts/checkings/edit_whole/{id}", 2)
                        .content(asJsonString(checkingToTest2))
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
                .perform(patch("/api/v1/accounts/checkings/update_status/{id}", 2)
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
                .perform(patch("/api/v1/accounts/checkings/update_balance/{id}", 2)
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