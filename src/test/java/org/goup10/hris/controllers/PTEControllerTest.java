package org.goup10.hris.controllers;

import org.goup10.hris.entities.PTE;
import org.goup10.hris.repositories.PTERepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class PTEControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PTERepository pteRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        pteRepository.deleteAll();
    }
    @Test
    public void givenPTEObject_whenCreatePTE_thenReturnSavedPTE() throws Exception{}
    @Test
    public void givenListOfPTE_whenGetAllPTE_thenReturnPTEList() throws Exception {}
    @Test
    public void givenPTEId_whenGetPTEById_thenReturnPTEObject() throws Exception{}
    @Test
    public void givenInvalidPTEId_whenGetPTEById_thenReturnEmpty() throws Exception{}
    @Test
    public void givenUpdatedPTE_whenUpdatePTE_thenReturnUpdatePTEObject() throws Exception{}
    @Test
    public void givenUpdatedPTE_whenUpdatePTE_thenReturn404() throws Exception{}
    @Test
    public void givenPTEId_whenDeletePTE_thenReturn200() throws Exception{}
    @Test
    public void givenPTEId_whenDeletePTE_thenReturn404() throws Exception{}
}
