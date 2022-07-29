package org.goup10.hris.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.goup10.hris.entities.Tax;
import org.goup10.hris.repositories.TaxRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.CoreMatchers.is;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TaxControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaxRepository taxRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        taxRepository.deleteAll();
    }
    @Test
    public void givenTaxObject_whenCreateTax_thenReturnSavedTax() throws Exception {
        Tax tax = Tax.builder().build();

        ResultActions resultActions = mockMvc.perform(post("/tax")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tax)));
        resultActions.andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.taxId", is(tax.getTaxId())));
    }
    @Test
    public void givenListOfTax_whenGetAllTax_thenReturnTaxList() throws Exception {
        List<Tax> taxList = new ArrayList<>();
        Tax tax1 = Tax.builder().build();
        Tax tax2 = Tax.builder().build();

        taxList.add(tax1);
        taxList.add(tax2);
        taxRepository.saveAll(taxList);

        ResultActions resultActions = mockMvc.perform(get("/tax"));
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(taxList)));
    }
    @Test
    public void givenTaxId_whenGetTaxById_thenReturnTaxObject() throws Exception{
        Tax tax = Tax.builder().build();
        Tax savedTax = taxRepository.save(tax);
        ResultActions resultActions = mockMvc.perform(get("/tax/{id}", savedTax.getTaxId()));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.taxId", is(tax.getTaxId())));

    }
    @Test
    public void givenInvalidTaxId_whenGetTaxById_thenReturnEmpty() throws Exception{
        Integer invalidTaxId = 1000;
        Tax tax = Tax.builder().build();
        Tax savedTax = taxRepository.save(tax);
        ResultActions resultActions = mockMvc.perform(put("/tax/{id}", invalidTaxId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedTax)));
        resultActions.andExpect(status().isNotFound()).andDo(print());
    }
    @Test
    public void givenUpdatedTax_whenUpdateTax_thenReturnUpdateTaxObject() throws Exception{
        Tax tax = Tax.builder().build();
        Tax savedTax = taxRepository.save(tax);

        savedTax.setFederal_prem(3);

        ResultActions resultActions = mockMvc.perform(put("/tax/{id}", savedTax.getTaxId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedTax)));

        resultActions.andExpect(status().isOk()).andDo(print())
                .andExpect(jsonPath("$.federal_prem", is(tax.getFederal_prem())));

    }
    @Test
    public void givenUpdatedTax_whenUpdateTax_thenReturn404() throws Exception{
        Integer invalidTaxId = 1000;
        Tax tax = Tax.builder().build();
        Tax savedTax = taxRepository.save(tax);
        ResultActions resultActions = mockMvc.perform(put("/tax/{id}", invalidTaxId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedTax)));
        resultActions.andExpect(status().isNotFound()).andDo(print());
    }
    @Test
    public void givenTaxId_whenDeleteTax_thenReturn200() throws Exception{
        Tax tax = Tax.builder().build();
        Tax savedTax = taxRepository.save(tax);

        ResultActions resultActions = mockMvc.perform(delete("/tax/{id}", savedTax.getTaxId()));
        resultActions.andExpect(status().isOk()).andDo(print());
    }
    @Test
    public void givenTaxId_whenDeleteTax_thenReturn404() throws Exception{
        Integer invalidTaxId = 1000;
        Tax tax = Tax.builder().build();
        Tax savedTax = taxRepository.save(tax);

        ResultActions resultActions = mockMvc.perform(delete("/tax/{id}", invalidTaxId));
        resultActions.andExpect(status().isNotFound()).andDo(print());
    }
}

