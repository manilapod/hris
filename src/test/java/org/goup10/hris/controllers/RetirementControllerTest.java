package org.goup10.hris.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.goup10.hris.entities.Retirement;
import org.goup10.hris.repositories.RetirementRepository;
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
public class RetirementControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RetirementRepository retirementRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        retirementRepository.deleteAll();
    }
    @Test
    public void givenRetirementObject_whenCreateRetirement_thenReturnSavedRetirement() throws Exception{}
    @Test
    public void givenListOfRetirement_whenGetAllRetirement_thenReturnRetirementList() throws Exception {}
    @Test
    public void givenRetirementId_whenGetRetirementById_thenReturnRetirementObject() throws Exception{}
    @Test
    public void givenInvalidRetirementId_whenGetRetirementById_thenReturnEmpty() throws Exception{}
    @Test
    public void givenUpdatedRetirement_whenUpdateRetirement_thenReturnUpdateRetirementObject() throws Exception{}
    @Test
    public void givenUpdatedRetirement_whenUpdateRetirement_thenReturn404() throws Exception{}
    @Test
    public void givenRetirementId_whenDeleteRetirement_thenReturn200() throws Exception{}
    @Test
    public void givenRetirementId_whenDeleteRetirement_thenReturn404() throws Exception{}
}
