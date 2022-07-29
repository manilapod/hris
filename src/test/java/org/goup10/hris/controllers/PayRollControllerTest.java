package org.goup10.hris.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.goup10.hris.entities.PayRoll;
import org.goup10.hris.repositories.PayRollRepository;
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
public class PayRollControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PayRollRepository payrollRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        payrollRepository.deleteAll();
    }
    @Test
    public void givenPayRollObject_whenCreatePayRoll_thenReturnSavedPayRoll() throws Exception {
        PayRoll payroll = PayRoll.builder()
                .payrollId(1)
                .employee_id(1)
                .hourly_rate(13l)
                .gross_salary(100l)
                .salary(80l)
                .yearly_bonus(10L)
                .state_tax(5l)
                .federal_tax(12l)
                .employment_gender(Long.valueOf(String.valueOf("M")))
                .weekly_hours(6)
                .pay_cycle("2 week")
                .extra_hour(4)
                .effective("yes")
                .build();

        ResultActions resultActions = mockMvc.perform(post("/payroll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payroll)));
        resultActions.andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.payrollId", is(payroll.getPayrollId())))
                .andExpect(jsonPath("$.employee_id", is(payroll.getEmployee_id())))
                .andExpect(jsonPath("$.gross_salary", is(payroll.getGross_salary())))
                .andExpect(jsonPath("$.pay_cycle", is(payroll.getPay_cycle())))
                .andExpect(jsonPath("$.effective", is(payroll.getEffective())))
                .andExpect(jsonPath("$.employment_gender", is(payroll.getEmployment_gender())))
                .andExpect(jsonPath("$.salary", is(payroll.getSalary())))
                .andExpect(jsonPath("$.yearly_bonus", is(payroll.getYearly_bonus())))
                .andExpect(jsonPath("$.state_tax", is(payroll.getState_tax())))
                .andExpect(jsonPath("$.federal_tax", is(payroll.getFederal_tax())))
                .andExpect(jsonPath("$.weekly_hours", is(payroll.getWeekly_hours())))
                .andExpect(jsonPath("$.extra_hour", is(payroll.getExtra_hour())))
                .andExpect(jsonPath("$.hourly_rate", is(payroll.getHourly_rate())));
    }
    @Test
    public void givenListOfPayRoll_whenGetAllPayRoll_thenReturnPayRollList() throws Exception {
        List<PayRoll> payrollList = new ArrayList<>();
        PayRoll payroll1 = PayRoll.builder()
                .payrollId(1)
                .employee_id(1)
                .hourly_rate(13l)
                .gross_salary(100l)
                .salary(80l)
                .yearly_bonus(10L)
                .state_tax(5l)
                .federal_tax(12l)
                .employment_gender(Long.valueOf(String.valueOf("M")))
                .weekly_hours(6)
                .pay_cycle("2 week")
                .extra_hour(4)
                .effective("yes")
                .build();
        PayRoll payroll2 = PayRoll.builder()
                .payrollId(1)
                .employee_id(1)
                .hourly_rate(13l)
                .gross_salary(100l)
                .salary(80l)
                .yearly_bonus(10L)
                .state_tax(5l)
                .federal_tax(12l)
                .employment_gender(Long.valueOf(String.valueOf("M")))
                .weekly_hours(6)
                .pay_cycle("2 week")
                .extra_hour(4)
                .effective("yes")
                .build();

        payrollList.add(payroll1);
        payrollList.add(payroll2);
        payrollRepository.saveAll(payrollList);

        ResultActions resultActions = mockMvc.perform(get("/payroll"));
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(payrollList)));
    }
    @Test
    public void givenPayRollId_whenGetPayRollById_thenReturnPayRollObject() throws Exception{
        PayRoll payroll = PayRoll.builder()
                .payrollId(1)
                .employee_id(1)
                .hourly_rate(13l)
                .gross_salary(100l)
                .salary(80l)
                .yearly_bonus(10L)
                .state_tax(5l)
                .federal_tax(12l)
                .employment_gender(Long.valueOf(String.valueOf("M")))
                .weekly_hours(6)
                .pay_cycle("2 week")
                .extra_hour(4)
                .effective("yes")
                .build();
        PayRoll savedPayRoll = payrollRepository.save(payroll);
        ResultActions resultActions = mockMvc.perform(get("/payroll/{id}", savedPayRoll.getPayrollId()));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.gross_salary", is(payroll.getGross_salary())))
                .andExpect(jsonPath("$.weekly_hours", is(payroll.getWeekly_hours())))
                .andExpect(jsonPath("$.extra_hour", is(payroll.getExtra_hour())))
                .andExpect(jsonPath("$.hourly_rate", is(payroll.getHourly_rate())));
    }
    @Test
    public void givenInvalidPayRollId_whenGetPayRollById_thenReturnEmpty() throws Exception{
        Integer invalidPayRollId = 1000;
        PayRoll payroll = PayRoll.builder()
                .payrollId(1)
                .employee_id(1)
                .hourly_rate(13l)
                .gross_salary(100l)
                .salary(80l)
                .yearly_bonus(10L)
                .state_tax(5l)
                .federal_tax(12l)
                .employment_gender(Long.valueOf(String.valueOf("M")))
                .weekly_hours(6)
                .pay_cycle("2 week")
                .extra_hour(4)
                .effective("yes")
                .build();
        PayRoll savedPayRoll = payrollRepository.save(payroll);
        ResultActions resultActions = mockMvc.perform(put("/payroll/{id}", invalidPayRollId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedPayRoll)));
        resultActions.andExpect(status().isNotFound()).andDo(print());
    }
    @Test
    public void givenUpdatedPayRoll_whenUpdatePayRoll_thenReturnUpdatePayRollObject() throws Exception{
        PayRoll payroll = PayRoll.builder()
                .payrollId(1)
                .employee_id(1)
                .hourly_rate(13l)
                .gross_salary(100l)
                .salary(80l)
                .yearly_bonus(10L)
                .state_tax(5l)
                .federal_tax(12l)
                .employment_gender(Long.valueOf(String.valueOf("M")))
                .weekly_hours(6)
                .pay_cycle("2 week")
                .extra_hour(4)
                .effective("yes")
                .build();
        PayRoll savedPayRoll = payrollRepository.save(payroll);

        savedPayRoll.getGross_salary();

        ResultActions resultActions = mockMvc.perform(put("/payroll/{id}", savedPayRoll.getPayrollId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedPayRoll)));

        resultActions.andExpect(status().isOk()).andDo(print())
                .andExpect(jsonPath("$.gross_salary", is(payroll.getGross_salary())));

    }
    @Test
    public void givenUpdatedPayRoll_whenUpdatePayRoll_thenReturn404() throws Exception{
        Integer invalidPayRollId = 1000;
        PayRoll payroll = PayRoll.builder()
                .payrollId(1)
                .employee_id(1)
                .hourly_rate(13l)
                .gross_salary(100l)
                .salary(80l)
                .yearly_bonus(10L)
                .state_tax(5l)
                .federal_tax(12l)
                .employment_gender(Long.valueOf(String.valueOf("M")))
                .weekly_hours(6)
                .pay_cycle("2 week")
                .extra_hour(4)
                .effective("yes")
                .build();
        PayRoll savedPayRoll = payrollRepository.save(payroll);
        ResultActions resultActions = mockMvc.perform(put("/payroll/{id}", invalidPayRollId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedPayRoll)));
        resultActions.andExpect(status().isNotFound()).andDo(print());
    }
    @Test
    public void givenPayRollId_whenDeletePayRoll_thenReturn200() throws Exception{
        PayRoll payroll = PayRoll.builder().build();
        PayRoll savedPayRoll = payrollRepository.save(payroll);

        ResultActions resultActions = mockMvc.perform(delete("/payroll/{id}", savedPayRoll.getPayrollId()));
        resultActions.andExpect(status().isOk()).andDo(print());
    }
    @Test
    public void givenPayRollId_whenDeletePayRoll_thenReturn404() throws Exception{
        Integer invalidPayRollId = 1000;
        PayRoll payroll = PayRoll.builder()
                .payrollId(1)
                .employee_id(1)
                .hourly_rate(13l)
                .gross_salary(100l)
                .salary(80l)
                .yearly_bonus(10L)
                .state_tax(5l)
                .federal_tax(12l)
                .employment_gender(Long.valueOf(String.valueOf("M")))
                .weekly_hours(6)
                .pay_cycle("2 week")
                .extra_hour(4)
                .effective("yes")
                .build();
        PayRoll savedPayRoll = payrollRepository.save(payroll);

        ResultActions resultActions = mockMvc.perform(delete("/payroll/{id}", invalidPayRollId));
        resultActions.andExpect(status().isNotFound()).andDo(print());
    }
}

