package org.goup10.hris.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.goup10.hris.entities.Insurance;
import org.goup10.hris.repositories.InsuranceRepository;
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
public class InsuranceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        insuranceRepository.deleteAll();
    }
    @Test
    public void givenInsuranceObject_whenCreateInsurance_thenReturnSavedInsurance() throws Exception{}
    @Test
    public void givenListOfInsurance_whenGetAllInsurance_thenReturnInsuranceList() throws Exception {}
    @Test
    public void givenInsuranceId_whenGetInsuranceById_thenReturnInsuranceObject() throws Exception{}
    @Test
    public void givenInvalidInsuranceId_whenGetInsuranceById_thenReturnEmpty() throws Exception{}
    @Test
    public void givenUpdatedInsurance_whenUpdateInsurance_thenReturnUpdateInsuranceObject() throws Exception{}
    @Test
    public void givenUpdatedInsurance_whenUpdateInsurance_thenReturn404() throws Exception{}
    @Test
    public void givenInsuranceId_whenDeleteInsurance_thenReturn200() throws Exception{}
    @Test
    public void givenInsuranceId_whenDeleteInsurance_thenReturn404() throws Exception{}
}
