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
    public void givenPayRollObject_whenCreatePayRoll_thenReturnSavedPayRoll() throws Exception{}
    @Test
    public void givenListOfPayRoll_whenGetAllPayRoll_thenReturnPayRollList() throws Exception {}
    @Test
    public void givenPayRollId_whenGetPayRollById_thenReturnPayRollObject() throws Exception{}
    @Test
    public void givenInvalidPayRollId_whenGetPayRollById_thenReturnEmpty() throws Exception{}
    @Test
    public void givenUpdatedPayRoll_whenUpdatePayRoll_thenReturnUpdatePayRollObject() throws Exception{}
    @Test
    public void givenUpdatedPayRoll_whenUpdatePayRoll_thenReturn404() throws Exception{}
    @Test
    public void givenPayRollId_whenDeletePayRoll_thenReturn200() throws Exception{}
    @Test
    public void givenPayRollId_whenDeletePayRoll_thenReturn404() throws Exception{}
}
