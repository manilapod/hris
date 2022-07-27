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
    public void givenTaxObject_whenCreateTax_thenReturnSavedTax() throws Exception{}
    @Test
    public void givenListOfTax_whenGetAllTax_thenReturnTaxList() throws Exception {}
    @Test
    public void givenTaxId_whenGetTaxById_thenReturnTaxObject() throws Exception{}
    @Test
    public void givenInvalidTaxId_whenGetTaxById_thenReturnEmpty() throws Exception{}
    @Test
    public void givenUpdatedTax_whenUpdateTax_thenReturnUpdateTaxObject() throws Exception{}
    @Test
    public void givenUpdatedTax_whenUpdateTax_thenReturn404() throws Exception{}
    @Test
    public void givenTaxId_whenDeleteTax_thenReturn200() throws Exception{}
    @Test
    public void givenTaxId_whenDeleteTax_thenReturn404() throws Exception{}

}
