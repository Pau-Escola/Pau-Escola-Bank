package com.ironhack.pauescolabank.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.pauescolabank.DTO.AccountHolderDTO;
import com.ironhack.pauescolabank.DTO.CreditDTO;
import com.ironhack.pauescolabank.embedded.Address;
import com.ironhack.pauescolabank.embedded.Money;
import com.ironhack.pauescolabank.enums.AccountStatus;
import com.ironhack.pauescolabank.model.Credit;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import com.ironhack.pauescolabank.repositories.AccountHolderRepository;
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
class AccountHolderControllerTest {

    WebApplicationContext webApplicationContext;
    AccountHolderRepository accountHolderRepository;

    private AccountHolder accountHolder;
    private MockMvc mockMvc;

    @Autowired
    public AccountHolderControllerTest(
            WebApplicationContext webApplicationContext,
            AccountHolderRepository accountHolderRepository) {
        this.webApplicationContext = webApplicationContext;
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



    }

    @AfterEach
    void tearDown() {
        accountHolderRepository.deleteAll();

    }

    @Test
    void getAll() throws Exception {
        var result = mockMvc
                .perform(get("/api/v1/users/accountholders"))
                .andExpect(status().isOk()) // check status code 200
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Paulo"));
    }

    @Test
    void getById() throws Exception {
        var result = mockMvc
                .perform(get("/api/v1/users/accountholders/{id}" , accountHolder.getId()))
                .andExpect(status().isOk()) // check status code 200
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Paulo"));
    }

    @Test
    void create() throws Exception {

        var newAccountHolder = new AccountHolderDTO(
                "Roberto", "Carlos","paulo@gmail.com", "3xW5gJ@",
                "Spain", "Reus", 43205, "Raval Robusto 25",
                2000, 05, 21, null);

        var result = mockMvc
                .perform(post("/api/v1/users/accountholders")
                        .content(asJsonString(newAccountHolder))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // check status code 200
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Roberto"));
    }

    @Test
    void delete() throws Exception {
        var result = mockMvc
                .perform(MockMvcRequestBuilders.delete("/api/v1/users/accountholders/{id}" , accountHolder.getId()))
                .andExpect(status().isOk()) // check status code 200
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("removed"));
    }

    @Test
    void updateAddress() throws Exception {
        var newAddress = new Address("Italy", "Parma", 32456, "Paseo Marinno");

        var result = mockMvc
                .perform(patch("/api/v1/users/accountholders/update_address/{id}", accountHolder.getId())
                        .content(asJsonString(newAddress))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // check status code 200
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Parma"));
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldOnlyAcceptOver18() throws Exception {

        var newAccountHolder = new AccountHolderDTO(
                "Roberto", "Carlos","paulo@gmail.com", "3xW5gJ@",
                "Spain", "Reus", 43205, "Raval Robusto 25",
                2020, 05, 21,null);

        assertThrows(Exception.class, ()-> mockMvc
                .perform(post("/api/v1/users/accountholders")
                        .content(asJsonString(newAccountHolder))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn());


    }

    @Test
    void shouldOnlyAcceptCorrectEmailFormat() throws Exception {

        var newAccountHolder = new AccountHolderDTO(
                "Roberto", "Carlos","paulogmail.com", "3xW5gJ@",
                "Spain", "Reus", 43205, "Raval Robusto 25",
                2000, 05, 21, null);

        assertThrows(Exception.class, ()-> mockMvc
                .perform(post("/api/v1/users/accountholders")
                        .content(asJsonString(newAccountHolder))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn());


    }


}