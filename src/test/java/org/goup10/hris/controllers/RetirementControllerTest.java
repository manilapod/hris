package org.goup10.hris.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.goup10.hris.entities.Retirement;
import org.goup10.hris.repositories.EmployeeRepository;
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
    public void givenRetirementObject_whenCreateRetirement_thenReturnSavedPayRoll() throws Exception {
        Retirement retirement = Retirement.builder().build();
                 retirement = Retirement.builder()
                .retirementId(1)
                .benefitId(1)
                .irsAMOUNT(50)
                .ptbAMOUNT(20)
                .tsaAMOUNT(20)
                .build();

        ResultActions resultActions = mockMvc.perform(post("/retirement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(retirement)));
        resultActions.andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.retirementId", is(retirement.getRetirementId())))
                .andExpect(jsonPath("$.retirementId", is(retirement.getRetirementId())))
                .andExpect(jsonPath("$.benefitId", is(retirement.getBenefitId())))
                .andExpect(jsonPath("$.irs_amount", is(retirement.getIrsAMOUNT())))
                .andExpect(jsonPath("$.ptb_amount", is(retirement.getPtbAMOUNT())))
                .andExpect(jsonPath("$.tsa_amount", is(retirement.getTsaAMOUNT())));
    }
    @Test
    public void givenListOfRetirement_whenGetAllRetirement_thenReturnRetirementList() throws Exception {
        List<Retirement> retirementList = new ArrayList<>();
        Retirement retirement1 = Retirement.builder().build();
        Retirement retirement2 = Retirement.builder().build();
        retirement1 = Retirement.builder()
                .retirementId(1)
                .benefitId(1)
                .irsAMOUNT(50)
                .ptbAMOUNT(20)
                .tsaAMOUNT(20)
                .build();
                 retirement2 = Retirement.builder()
                .retirementId(1)
                .benefitId(1)
                .irsAMOUNT(50)
                .ptbAMOUNT(20)
                .tsaAMOUNT(20)
                .build();

        retirementList.add(retirement1);
        retirementList.add(retirement2);
    }
    @Test
    public void givenRetirementId_whenGetRetirementById_thenReturnRetirementObject() throws Exception{
        Retirement retirement = Retirement.builder().build();
        retirement = Retirement.builder()
                .retirementId(1)
                .benefitId(1)
                .irsAMOUNT(50)
                .ptbAMOUNT(20)
                .tsaAMOUNT(20)
                .build();
        Retirement savedRetirement = retirementRepository.save(retirement);
        ResultActions resultActions = mockMvc.perform(get("/retirement/{id}", savedRetirement.getRetirementId()));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.retirementId", is(retirement.getRetirementId())))
                .andExpect(jsonPath("$.irs_amount", is(retirement.getIrsAMOUNT())))
                .andExpect(jsonPath("$.ptb_amount", is(retirement.getPtbAMOUNT())))
                .andExpect(jsonPath("$.tsa_amount", is(retirement.getTsaAMOUNT())));
    }
    @Test
    public void givenInvalidRetirementId_whenGetRetirementById_thenReturnEmpty() throws Exception{
        Integer invalidRetirementId = 1000;
        Retirement retirement = Retirement.builder().build();
         retirement = Retirement.builder()
                .retirementId(1)
                .benefitId(1)
                .irsAMOUNT(50)
                .ptbAMOUNT(20)
                .tsaAMOUNT(20)
                .build();
        Retirement savedRetirement = retirementRepository.save(retirement);
        ResultActions resultActions = mockMvc.perform(put("/retirement/{id}", invalidRetirementId)
                .contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    public void givenUpdatedRetirement_whenUpdateRetirement_thenReturnUpdateRetirementObject() throws Exception {
        Retirement retirement = Retirement.builder().build();
        retirement = Retirement.builder()
                .retirementId(1)
                .benefitId(1)
                .irsAMOUNT(50)
                .ptbAMOUNT(20)
                .tsaAMOUNT(20)
                .build();
        Retirement savedRetirement = retirementRepository.save(retirement);

        savedRetirement.getIrsAMOUNT();
    }
        @Test
        public void givenUpdatedRetirement_whenUpdateRetirement_thenReturn404() throws Exception{
            Integer invalidRetirementId = 1000;
            Retirement retirement = Retirement.builder().build();
            retirement = Retirement.builder()
                    .retirementId(1)
                    .benefitId(1)
                    .irsAMOUNT(50)
                    .ptbAMOUNT(20)
                    .tsaAMOUNT(20)
                    .build();
            Retirement savedRetirement = retirementRepository.save(retirement);
            ResultActions resultActions = mockMvc.perform(put("/retirement/{id}", invalidRetirementId)
                    .contentType(MediaType.APPLICATION_JSON));
        }
        @Test
        public void givenRetirementId_whenDeleteRetirement_thenReturn200() throws Exception {
            Retirement retirement = Retirement.builder().build();
            retirement = Retirement.builder()
                    .retirementId(1)
                    .benefitId(1)
                    .irsAMOUNT(50)
                    .ptbAMOUNT(20)
                    .tsaAMOUNT(20)
                    .build();
            Retirement savedRetirement = retirementRepository.save(retirement);

            ResultActions resultActions = mockMvc.perform(delete("/retirement/{id}", savedRetirement.getRetirementId()));
        }
            @Test
            public void givenRetirementId_whenDeleteRetirement_thenReturn404 () throws Exception {
                Integer invalidRetirementId = 1000;
                Retirement retirement = Retirement.builder().build();
                retirement = Retirement.builder()
                        .retirementId(1)
                        .benefitId(1)
                        .irsAMOUNT(50)
                        .ptbAMOUNT(20)
                        .tsaAMOUNT(20)
                        .build();
                Retirement savedRetirement = retirementRepository.save(retirement);

            }
        }
