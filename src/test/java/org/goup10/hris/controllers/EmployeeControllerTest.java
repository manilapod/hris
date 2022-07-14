package org.goup10.hris.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.goup10.hris.entities.Employee;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        employeeRepository.deleteAll();
    }

    @Test
    public void givenEmployeeObject_whenCreateEmployee_thenReturnSavedEmployee() throws Exception{
        // given - precondition or setup
        Employee employee = Employee.builder()
                .benefitId(1)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@gmail.com")
                .birthDate(Date.valueOf("1985-10-20"))
                .jobRole("Student")
                .address("123 Awesome Street, Indianapolis, IN 46075")
                .telephoneNumber("222-333-4444")
                .state("IN")
                .inTraining(true)
                .performance(8)
                .startedDate(Date.valueOf("2022-01-15"))
                .lastUpdate("Learning brilliantly!")
                .build();

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        // then - verify the result or output using assert statements
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.benefitId", is(employee.getBenefitId())))
                .andExpect(jsonPath("$.firstName", is(employee.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(employee.getLastName())))
                .andExpect(jsonPath("$.email", is(employee.getEmail())))
//                .andExpect(jsonPath("$.birthDate", is(employee.getBirthDate())))
                .andExpect(jsonPath("$.jobRole", is(employee.getJobRole())))
                .andExpect(jsonPath("$.address", is(employee.getAddress())))
                .andExpect(jsonPath("$.telephoneNumber", is(employee.getTelephoneNumber())))
                .andExpect(jsonPath("$.state", is(employee.getState())))
                .andExpect(jsonPath("$.inTraining", is(employee.getInTraining())))
                .andExpect(jsonPath("$.performance", is(employee.getPerformance())))
//                .andExpect(jsonPath("$.startedDate", is(employee.getStartedDate())))
                .andExpect(jsonPath("$.lastUpdate", is(employee.getLastUpdate())));
    }
}
