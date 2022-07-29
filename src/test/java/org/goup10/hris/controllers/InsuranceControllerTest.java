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
    public void givenInsuranceObject_whenCreateInsurance_thenReturnSavedInsurance() throws Exception{
        Insurance insurance = Insurance.builder()
                .insuranceId(1)
                .benefitId(1)
                .health_prem(3)
                .life_prem(5)
                .build();
        ResultActions resultActions = mockMvc.perform(post("/insurance")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(insurance)));
        resultActions.andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.insuranceId", is(insurance.getInsuranceId())))
                .andExpect(jsonPath("$.benefitId", is(insurance.getBenefitId())))
                .andExpect(jsonPath("$.health_prem", is(insurance.getHealth_prem())))
                .andExpect(jsonPath("$.life_prem", is(insurance.getLife_prem())));
    }
    @Test
    public void givenListOfInsurance_whenGetAllInsurance_thenReturnInsuranceList() throws Exception {
        List<Insurance> insuranceList = new ArrayList<>();
        Insurance insurance1 = Insurance.builder().insuranceId(1)
                .benefitId(1)
                .health_prem(3)
                .life_prem(5)
                .build();
        Insurance insurance2 = Insurance.builder().insuranceId(1)
                .benefitId(1)
                .health_prem(3)
                .life_prem(5)
                .build();
        insuranceList.add(insurance1);
        insuranceList.add(insurance2);
        insuranceRepository.saveAll(insuranceList);

        ResultActions resultActions = mockMvc.perform(get("/insurance"));
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(insuranceList)));
    }
    @Test
    public void givenInsuranceId_whenGetInsuranceById_thenReturnInsuranceObject() throws Exception{
        Insurance insurance = Insurance.builder()
                .benefitId(1)
                .health_prem(3)
                .life_prem(5)
                .build();
        Insurance savedInsurance = insuranceRepository.save(insurance);

        ResultActions resultActions = mockMvc.perform(get("/insurance/{id}", savedInsurance.getInsuranceId()));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.health_prem", is(insurance.getHealth_prem())))
                .andExpect(jsonPath("$.life_prem", is(insurance.getLife_prem())));
    }
    @Test
    public void givenInvalidInsuranceId_whenGetInsuranceById_thenReturnEmpty() throws Exception{
        Integer invalidInsuranceId = 1000;
        Insurance insurance = Insurance.builder()
                .benefitId(1)
                .health_prem(3)
                .life_prem(5)
                .build();
        Insurance savedInsurance = insuranceRepository.save(insurance);
        ResultActions resultActions = mockMvc.perform(put("/insurance/{id}", invalidInsuranceId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedInsurance)));
        resultActions.andExpect(status().isNotFound()).andDo(print());
    }
    @Test
    public void givenUpdatedInsurance_whenUpdateInsurance_thenReturnUpdateInsuranceObject() throws Exception{
        Insurance insurance = Insurance.builder()
                .benefitId(1)
                .health_prem(3)
                .life_prem(5)
                .build();
        Insurance savedInsurace = insuranceRepository.save(insurance);

        savedInsurace.getHealth_prem();

        ResultActions resultActions = mockMvc.perform(put("/insurance/{id}", savedInsurace.getInsuranceId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedInsurace)));

        resultActions.andExpect(status().isOk()).andDo(print())
                .andExpect(jsonPath("$.health_prem", is(insurance.getHealth_prem())));

    }
    @Test
    public void givenUpdatedInsurance_whenUpdateInsurance_thenReturn404() throws Exception{
        Integer invalidInsuranceId = 1000;
        Insurance insurance = Insurance.builder()
                .benefitId(1)
                .health_prem(3)
                .life_prem(5)
                .build();
        Insurance savedInsurance = insuranceRepository.save(insurance);
        ResultActions resultActions = mockMvc.perform(put("/insurance/{id}", invalidInsuranceId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedInsurance)));
        resultActions.andExpect(status().isNotFound()).andDo(print());
    }
    @Test
    public void givenInsuranceId_whenDeleteInsurance_thenReturn200() throws Exception{
        Insurance insurance = Insurance.builder()
                .benefitId(1)
                .health_prem(3)
                .life_prem(5)
                .build();
        Insurance savedInsurance = insuranceRepository.save(insurance);
        ResultActions resultActions = mockMvc.perform(delete("/insurance/{id}", savedInsurance.getInsuranceId()));
        resultActions.andExpect(status().isOk()).andDo(print());
    }
    @Test
    public void givenInsuranceId_whenDeleteInsurance_thenReturn404() throws Exception{
        int invalidInsuranceId = 1000;
        Insurance insurance = Insurance.builder()
                .benefitId(1)
                .health_prem(3)
                .life_prem(5)
                .build();
        Insurance savedInsurance = insuranceRepository.save(insurance);
        ResultActions resultActions = mockMvc.perform(delete("/insurance/{id}", invalidInsuranceId));
        resultActions.andExpect(status().isNotFound()).andDo(print());
    }
}
