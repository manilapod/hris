package org.goup10.hris.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.goup10.hris.entities.Payroll;
import org.goup10.hris.repositories.EmployeeRepository;
import org.goup10.hris.repositories.PayRollRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PayrollControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PayRollRepository payRollRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
//        payRollRepository.deleteAll();
    }

    @Test
    public void givenPayRollObject_whenCreatePayRoll_thenReturnSavedPayRoll() throws Exception {
        Payroll payroll = Payroll.builder()
                .payrollId(1)
                .employeeId(362)
                .hourlyRate(13l)
                .grossSalary(100l)
                .salary(80l)
                .yearlyBonus(10L)
                .stateTax(5l)
                .federalTax(12l)
                .employmentType("PartTime")
                .weeklyHours(6)
                .payCycle("2 week")
                .extraHours(4)
                .effective("yes")
                .build();

        ResultActions resultActions = mockMvc.perform(post("/payroll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payroll)));

        resultActions.andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.payrollId", is(payroll.getPayrollId())))
                .andExpect(jsonPath("$.payrollId", is(payroll.getPayrollId())))
                .andExpect(jsonPath("$.employeeId", is(payroll.getEmployeeId())))
                .andExpect(jsonPath("$.grossSalary", is(payroll.getGrossSalary())))
                .andExpect(jsonPath("$.payCycle", is(payroll.getPayCycle())))
                .andExpect(jsonPath("$.effective", is(payroll.getEffective())))
                .andExpect(jsonPath("$.employmentType", is(payroll.getEmploymentType())))
                .andExpect(jsonPath("$.salary", is(payroll.getSalary())))
                .andExpect(jsonPath("$.yearlyBonus", is(payroll.getYearlyBonus())))
                .andExpect(jsonPath("$.stateTax", is(payroll.getStateTax())))
                .andExpect(jsonPath("$.federalTax", is(payroll.getFederalTax())))
                .andExpect(jsonPath("$.weeklyHours", is(payroll.getWeeklyHours())))
                .andExpect(jsonPath("$.extraHour", is(payroll.getExtraHours())))
                .andExpect(jsonPath("$.hourlyRate", is(payroll.getHourlyRate())));
    }

    @Test
    public void givenListOfPayRoll_whenGetAllPayRoll_thenReturnPayRollList() throws Exception {
        List<Payroll> payrollList = new ArrayList<>();
        Payroll payroll1 = Payroll.builder().build();
        Payroll payroll2 = Payroll.builder().build();
        payroll1 = Payroll.builder()
                .payrollId(1)
                .employeeId(1)
                .hourlyRate(13l)
                .grossSalary(100l)
                .salary(80l)
                .yearlyBonus(10L)
                .stateTax(5l)
                .federalTax(12l)
                .employmentType("FullTime")
                .weeklyHours(6)
                .payCycle("2 week")
                .extraHours(4)
                .effective("yes")
                .build();
        payroll2 = Payroll.builder()
                .payrollId(1)
                .employeeId(1)
                .hourlyRate(13l)
                .grossSalary(100l)
                .salary(80l)
                .yearlyBonus(10L)
                .stateTax(5l)
                .federalTax(12l)
                .employmentType("FullTime")
                .weeklyHours(6)
                .payCycle("2 week")
                .extraHours(4)
                .effective("yes")
                .build();

        payrollList.add(payroll1);
        payrollList.add(payroll2);
        payRollRepository.saveAll(payrollList);

    }

    @Test
    public void givenPayRollId_whenGetPayRollById_thenReturnPayRollObject() throws Exception {
        Payroll payroll = Payroll.builder().build();
        payroll = Payroll.builder()
                .payrollId(1)
                .employeeId(1)
                .hourlyRate(13l)
                .grossSalary(100l)
                .salary(80l)
                .yearlyBonus(10L)
                .stateTax(5l)
                .federalTax(12l)
                .employmentType("FullTime")
                .effective(String.valueOf(Long.valueOf(String.valueOf("M"))))
                .weeklyHours(6)
                .payCycle("2 week")
                .extraHours(4)
                .effective("yes")
                .build();
        Payroll savedPayroll = payRollRepository.save(payroll);
        ResultActions resultActions = mockMvc.perform(get("/payroll/{id}", savedPayroll.getPayrollId()));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.payrollId", is(payroll.getPayrollId())))
                .andExpect(jsonPath("$.gross_salary", is(payroll.getGrossSalary())))
                .andExpect(jsonPath("$.weekly_hours", is(payroll.getWeeklyHours())))
                .andExpect(jsonPath("$.extra_hour", is(payroll.getExtraHours())))
                .andExpect(jsonPath("$.hourly_rate", is(payroll.getHourlyRate())));
    }

    @Test
    public void givenInvalidPayRollId_whenGetPayRollById_thenReturnEmpty() throws Exception {
        Integer invalidPayRollId = 1000;
        Payroll payroll = Payroll.builder().build();
        payroll = Payroll.builder()
                .payrollId(1)
                .employeeId(1)
                .hourlyRate(13l)
                .grossSalary(100l)
                .salary(80l)
                .yearlyBonus(10L)
                .stateTax(5l)
                .federalTax(12l)
                .employmentType("PartTime")
                .weeklyHours(6)
                .payCycle("2 week")
                .extraHours(4)
                .effective("yes")
                .build();
        Payroll savedPayroll = payRollRepository.save(payroll);
        ResultActions resultActions = mockMvc.perform(put("/payroll/{id}", invalidPayRollId)
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void givenUpdatedPayRoll_whenUpdatePayRoll_thenReturnUpdatePayRollObject() throws Exception {
        Payroll payroll = Payroll.builder().build();
        payroll = Payroll.builder()
                .payrollId(1)
                .employeeId(1)
                .hourlyRate(13l)
                .grossSalary(100l)
                .salary(80l)
                .yearlyBonus(10L)
                .stateTax(5l)
                .federalTax(12l)
                .employmentType("PartTime")
                .weeklyHours(6)
                .payCycle("2 week")
                .extraHours(4)
                .effective("yes")
                .build();
        Payroll savedPayroll = payRollRepository.save(payroll);

        savedPayroll.getGrossSalary();
    }

    @Test
    public void givenUpdatedPayRoll_whenUpdatePayRoll_thenReturn404() throws Exception {
        Integer invalidPayRollId = 1000;
        Payroll payroll = Payroll.builder().build();
        payroll = Payroll.builder()
                .payrollId(1)
                .employeeId(1)
                .hourlyRate(13l)
                .grossSalary(100l)
                .salary(80l)
                .yearlyBonus(10L)
                .stateTax(5l)
                .federalTax(12l)
                .employmentType("PartTime")
                .weeklyHours(6)
                .payCycle("2 week")
                .extraHours(4)
                .effective("yes")
                .build();
        Payroll savedPayroll = payRollRepository.save(payroll);
        ResultActions resultActions = mockMvc.perform(put("/payroll/{id}", invalidPayRollId)
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void givenPayRollId_whenDeletePayRoll_thenReturn404() throws Exception {
        Integer invalidPayRollId = 1000;
        Payroll payroll = Payroll.builder().build();
        payroll = Payroll.builder()
                .payrollId(1)
                .employeeId(1)
                .hourlyRate(13l)
                .grossSalary(100l)
                .salary(80l)
                .yearlyBonus(10L)
                .stateTax(5l)
                .federalTax(12l)
                .employmentType("PartTime")
                .weeklyHours(6)
                .payCycle("2 week")
                .extraHours(4)
                .effective("yes")
                .build();
        Payroll savedPayroll = payRollRepository.save(payroll);

        ResultActions resultActions = mockMvc.perform(delete("/payroll/{id}", invalidPayRollId));
    }
}
