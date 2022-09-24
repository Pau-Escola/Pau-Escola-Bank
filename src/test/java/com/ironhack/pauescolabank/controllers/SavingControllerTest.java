package com.ironhack.pauescolabank.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.pauescolabank.DTO.AccountHolderDTO;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
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
        var accountHolderTest = new AccountHolder("Paulo","Costas","test@gmail.com", "Ew4X5rT",
                LocalDate.of(2000,03,23),
                addressTest, null);
        accountHolder = accountHolderRepository.save(accountHolderTest);
        var savingToTest = new Saving();
        savingToTest.setSecretKey("ES7521004586");
        savingToTest.setAccountStatus(AccountStatus.ACTIVE);
        savingToTest.setBalance(BigDecimal.valueOf(700));
        savingToTest.setOwner(accountHolderTest);
        savingToTest.setInterestRate(0.35);
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

        var savingToTest2 = new SavingDTO(
                "ES75210046",  BigDecimal.valueOf(700), 0.42, BigDecimal.valueOf(700) );

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
        var balanceTotest = BigDecimal.valueOf(45000);
        var result = mockMvc
                .perform(patch("/api/v1/accounts/savings/update_balance/{id}", account.getId())
                        .content(asJsonString(balanceTotest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // check status code 200
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("45000"));
    }

    @Test
    void shouldOnlyAcceptMinimumBalanceBetween100And1000(){


        var savingToTest3 = new SavingDTO(
                "ES75210046", BigDecimal.valueOf(700), 0.28, BigDecimal.valueOf(50) );

        assertThrows(Exception.class, ()-> mockMvc
                .perform(post("/api/v1/accounts/savings/{id}", accountHolder.getId())
                        .content(asJsonString(savingToTest3))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn());


    }

    @Test
    void shouldOnlyAcceptInterestRateBetween0_0025And0_5() {


        var savingToTest3 = new SavingDTO(
                "ES75210046", BigDecimal.valueOf(700), 0.8, BigDecimal.valueOf(150) );

        assertThrows(Exception.class, ()-> mockMvc
                .perform(post("/api/v1/accounts/savings/{id}", accountHolder.getId())
                        .content(asJsonString(savingToTest3))
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