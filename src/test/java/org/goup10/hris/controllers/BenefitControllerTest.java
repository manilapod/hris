package org.goup10.hris.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.goup10.hris.entities.Benefit;
import org.goup10.hris.repositories.BenefitRepository;
import org.goup10.hris.repositories.EmployeeRepository;
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
public class BenefitControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BenefitRepository benefitRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        benefitRepository.deleteAll();
    }


    @Test
    public void givenBenefitObject_whenCreateBenefit_thenReturnSavedBenefit() throws Exception {
        Benefit benefit = Benefit.builder()
                .benefitId(1)
                .insuranceId(1)
                .employeeId(1)
                .retirementId(1)
                .lifeInsurance("Term life insurance")
                .retirementPlans("GIA")
                .tuitionAssistance("Tuition Guarantee Plan")
                .build();

        ResultActions resultActions = mockMvc.perform(post("/benefit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(benefit)));
        resultActions.andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.benefitId", is(benefit.getBenefitId())))
                .andExpect(jsonPath("$.benefitId", is(benefit.getBenefitId())))
                .andExpect(jsonPath("$.employeeId", is(benefit.getEmployeeId())))
                .andExpect(jsonPath("$.retirementId", is(benefit.getRetirementId())))
                .andExpect(jsonPath("$.insuranceId", is(benefit.getInsuranceId())))
                .andExpect(jsonPath("$.retirement_plans", is(benefit.getRetirementPlans())))
                .andExpect(jsonPath("$.tuition_assistance", is(benefit.getTuitionAssistance())))
                .andExpect(jsonPath("$.life_insurance", is(benefit.getLifeInsurance())));
    }

    @Test
    public void givenListOfBenefit_whenGetAllBenefit_thenReturnBenefitList() throws Exception {
        List<Benefit> benefitList = new ArrayList<>();
        Benefit benefit1 = Benefit.builder().build();
        Benefit benefit2 = Benefit.builder().build();
        benefit1 = Benefit.builder()
                .benefitId(1)
                .insuranceId(1)
                .employeeId(1)
                .retirementId(1)
                .lifeInsurance("Term life insurance")
                .retirementPlans("GIA")
                .tuitionAssistance("Tuition Guarantee Plan")
                .build();
        benefit2 = Benefit.builder()
                .benefitId(1)
                .insuranceId(1)
                .employeeId(1)
                .retirementId(1)
                .lifeInsurance("Term life insurance")
                .retirementPlans("GIA")
                .tuitionAssistance("Tuition Guarantee Plan")
                .build();

        benefitList.add(benefit1);
        benefitList.add(benefit2);
        benefitRepository.saveAll(benefitList);

    }

    @Test
    public void givenBenefitId_whenGetBenefitById_thenReturnBenefitObject() throws Exception {
        Benefit benefit = Benefit.builder()
                .benefitId(1)
                .insuranceId(1)
                .employeeId(1)
                .retirementId(1)
                .lifeInsurance("Term life insurance")
                .retirementPlans("GIA")
                .tuitionAssistance("Tuition Guarantee Plan")
                .build();

        Benefit savedBenefit = benefitRepository.save(benefit);
        ResultActions resultActions = mockMvc.perform(get("/benefit/{id}", savedBenefit.getBenefitId()));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.benefitId", is(benefit.getBenefitId())))
                .andExpect(jsonPath("$.retirement_plans", is(benefit.getRetirementPlans())))
                .andExpect(jsonPath("$.tuition_assistance", is(benefit.getTuitionAssistance())))
                .andExpect(jsonPath("$.life_insurance", is(benefit.getLifeInsurance())));

    }

    @Test
    public void givenInvalidBenefitId_whenGetBenefitById_thenReturnEmpty() throws Exception {
        Integer invalidBenefitId = 1000;
        Benefit benefit = Benefit.builder()
                .benefitId(1)
                .insuranceId(1)
                .employeeId(1)
                .retirementId(1)
                .lifeInsurance("Term life insurance")
                .retirementPlans("GIA")
                .tuitionAssistance("Tuition Guarantee Plan")
                .build();
        Benefit savedBenefit = benefitRepository.save(benefit);
        ResultActions resultActions = mockMvc.perform(put("/benefit/{id}", invalidBenefitId));
    }

    @Test
    public void givenUpdatedBenefit_whenUpdateBenefit_thenReturnUpdateBenefitObject() throws Exception {
        Benefit benefit = Benefit.builder()
                .benefitId(1)
                .insuranceId(1)
                .employeeId(1)
                .retirementId(1)
                .lifeInsurance("Term life insurance")
                .retirementPlans("GIA")
                .tuitionAssistance("Tuition Guarantee Plan")
                .build();
        Benefit savedBenefit = benefitRepository.save(benefit);
    }

    public void givenUpdatedBenefit_whenUpdateBenefit_thenReturn404() throws Exception {
        Integer invalidBenefitId = 1000;
        Benefit benefit = Benefit.builder()
                .benefitId(1)
                .insuranceId(1)
                .employeeId(1)
                .retirementId(1)
                .lifeInsurance("Term life insurance")
                .retirementPlans("GIA")
                .tuitionAssistance("Tuition Guarantee Plan")
                .build();
        Benefit savedBenefit = benefitRepository.save(benefit);
        ResultActions resultActions = mockMvc.perform(put("/benefit/{id}", invalidBenefitId));
    }

    @Test
    public void givenBenefitId_whenDeleteBenefit_thenReturn200() throws Exception {
        Benefit benefit = Benefit.builder()
                .benefitId(1)
                .insuranceId(1)
                .employeeId(1)
                .retirementId(1)
                .lifeInsurance("Term life insurance")
                .retirementPlans("GIA")
                .tuitionAssistance("Tuition Guarantee Plan")
                .build();

        Benefit savedBenefit = benefitRepository.save(benefit);
    }

    @Test


    public void givenBenefitId_whenDeleteBenefit_thenReturn404() throws Exception {
        Integer invalidBenefitId = 1000;
        Benefit benefit = Benefit.builder()
                .benefitId(1)
                .insuranceId(1)
                .employeeId(1)
                .retirementId(1)
                .lifeInsurance("Term life insurance")
                .retirementPlans("GIA")
                .tuitionAssistance("Tuition Guarantee Plan")
                .build();
        Benefit savedBenefit = benefitRepository.save(benefit);
    }
}

