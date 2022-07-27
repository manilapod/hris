package org.goup10.hris.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.goup10.hris.entities.Benefit;
import org.goup10.hris.repositories.BenefitRepository;
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
public class BenefitControllerTest{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BenefitRepository benefitRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        benefitRepository.deleteAll();
    }
    @Test
    public void givenBenefitObject_whenCreateBenefit_thenReturnSavedBenefit() throws Exception{}
    @Test
    public void givenListOfBenefit_whenGetAllBenefit_thenReturnBenefitList() throws Exception {}
    @Test
    public void givenBenefitId_whenGetBenefitById_thenReturnBenefitObject() throws Exception{}
    @Test
    public void givenInvalidBenefitId_whenGetBenefitById_thenReturnEmpty() throws Exception{}
    @Test
    public void givenUpdatedBenefit_whenUpdateBenefit_thenReturnUpdateBenefitObject() throws Exception{}
    @Test
    public void givenUpdatedBenefit_whenUpdateBenefit_thenReturn404() throws Exception{}
    @Test
    public void givenBenefitId_whenDeleteBenefit_thenReturn200() throws Exception{
    }
    @Test
    public void givenBenefitId_whenDeleteBenefit_thenReturn404() throws Exception{
    }
}
