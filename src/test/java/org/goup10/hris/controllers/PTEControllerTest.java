package org.goup10.hris.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.goup10.hris.entities.PTE;

import org.goup10.hris.repositories.PTERepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import java.sql.Timestamp;
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
    public void givenPTEObject_whenCreatePTE_thenReturnSavedPTE() throws Exception {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        PTE pte = PTE.builder()
                .pteId(10)
                .trainingId(10)
                .email("abc@def.com")
                .appStatus("Submitted")
                .firstName("John")
                .lastName("Doe")
                .position("Accountant")
                .zipCode(46075)
                .dataApplied(now.toString())
                .lastUpdate(now)
                .build();

        ResultActions resultActions = mockMvc.perform(post("/pte")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pte)));
        resultActions.andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.pteId", is(pte.getPteId())))
                .andExpect(jsonPath("$.pteId", is(pte.getPteId())))
                .andExpect(jsonPath("$.trainingId", is(pte.getTrainingId())))
                .andExpect(jsonPath("$.email", is(pte.getEmail())))
                .andExpect(jsonPath("$.app_status", is(pte.getAppStatus())))
                .andExpect(jsonPath("$.first_name", is(pte.getFirstName())))
                .andExpect(jsonPath("$.last_name", is(pte.getLastName())))
                .andExpect(jsonPath("$.position", is(pte.getPosition())))
                .andExpect(jsonPath("$.zip_code", is(pte.getZipCode())))
                .andExpect(jsonPath("$.data_applied", is(pte.getDataApplied())))
                .andExpect(jsonPath("$.last_update", is(pte.getLastUpdate())));
    }
    @Test
    public void givenListOfPTE_whenGetAllPTE_thenReturnPTEList() throws Exception {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        List<PTE> pteList = new ArrayList<>();
        PTE pte1 = PTE.builder().build();
        PTE pte2 = PTE.builder().build();
         pte1 = PTE.builder()
                 .pteId(10)
                 .trainingId(10)
                 .email("abc@def.com")
                 .appStatus("Submitted")
                 .firstName("John")
                 .lastName("Doe")
                 .position("Accountant")
                 .zipCode(46075)
                 .dataApplied(now.toString())
                 .lastUpdate(now)
                 .build();


        pte2 = PTE.builder()
                .pteId(15)
                .trainingId(18)
                .email("abg@def.com")
                .appStatus("Ready")
                .firstName("James")
                .lastName("Smith")
                .position("Manager")
                .zipCode(46067)
                .dataApplied(now.toString())
                .lastUpdate(now)
                .build();

        pteList.add(pte1);
        pteList.add(pte2);
        }

        @Test
        public void givenPTEId_whenGetPTEById_thenReturnPTEObject() throws Exception{
            Timestamp now = new Timestamp(System.currentTimeMillis());

            PTE pte = PTE.builder().build();
            pte = PTE.builder()
                    .pteId(10)
                    .trainingId(10)
                    .email("abc@def.com")
                    .appStatus("Submitted")
                    .firstName("John")
                    .lastName("Doe")
                    .position("Accountant")
                    .zipCode(46075)
                    .dataApplied(now.toString())
                    .lastUpdate(now)
                    .build();
            PTE savedPTE = pteRepository.save(pte);
            ResultActions resultActions = mockMvc.perform(get("/pte/{id}", savedPTE.getPteId()));
            resultActions.andExpect(status().isOk())
                    .andExpect(jsonPath("$.pteId", is(pte.getPteId())))
                .andExpect(jsonPath("$.first_name", is(pte.getFirstName())))
                    .andExpect(jsonPath("$.last_name", is(pte.getLastName())))
                    .andExpect(jsonPath("$.position", is(pte.getPosition())));

        }
        @Test
        public void givenInvalidPTEId_whenGetPTEById_thenReturnEmpty() throws Exception{
            Timestamp now = new Timestamp(System.currentTimeMillis());
            Integer invalidPTEId = 1000;
            PTE pte = PTE.builder().build();
            pte = PTE.builder()
                    .pteId(10)
                    .trainingId(10)
                    .email("abc@def.com")
                    .appStatus("Submitted")
                    .firstName("John")
                    .lastName("Doe")
                    .position("Accountant")
                    .zipCode(46075)
                    .dataApplied(now.toString())
                    .lastUpdate(now)
                    .build();
            PTE savedPTE = pteRepository.save(pte);
            ResultActions resultActions = mockMvc.perform(put("/pte/{id}", invalidPTEId)
                    .contentType(MediaType.APPLICATION_JSON));
            }
            @Test
            public void givenUpdatedPTE_whenUpdatePTE_thenReturnUpdatePTEObject() throws Exception {
                Timestamp now = new Timestamp(System.currentTimeMillis());
                PTE pte = PTE.builder().build();
                pte = PTE.builder()
                        .pteId(10)
                        .trainingId(10)
                        .email("abc@def.com")
                        .appStatus("Submitted")
                        .firstName("John")
                        .lastName("Doe")
                        .position("Accountant")
                        .zipCode(46075)
                        .dataApplied(now.toString())
                        .lastUpdate(now)
                        .build();
                PTE savedPTE = pteRepository.save(pte);

                savedPTE.getFirstName();
            }
            @Test
                public void givenUpdatedPTE_whenUpdatePTE_thenReturn404() throws Exception{
                Timestamp now = new Timestamp(System.currentTimeMillis());
                Integer invalidPTEId = 1000;
                    PTE pte = PTE.builder().build();
                    pte = PTE.builder()
                            .pteId(10)
                            .trainingId(10)
                            .email("abc@def.com")
                            .appStatus("Submitted")
                            .firstName("John")
                            .lastName("Doe")
                            .position("Accountant")
                            .zipCode(46075)
                            .dataApplied(now.toString())
                            .lastUpdate(now)
                            .build();
                    PTE savedPTE = pteRepository.save(pte);
                    ResultActions resultActions = mockMvc.perform(put("/pte/{id}", invalidPTEId)
                            .contentType(MediaType.APPLICATION_JSON));
                    }
                    @Test
                    public void givenPTEId_whenDeletePTE_thenReturn200() throws Exception{
                        Timestamp now = new Timestamp(System.currentTimeMillis());
                        PTE pte = PTE.builder().build();
                        pte = PTE.builder()
                                .pteId(10)
                                .trainingId(10)
                                .email("abc@def.com")
                                .appStatus("Submitted")
                                .firstName("John")
                                .lastName("Doe")
                                .position("Accountant")
                                .zipCode(46075)
                                .dataApplied(now.toString())
                                .lastUpdate(now)
                                .build();
                        PTE savedPTE = pteRepository.save(pte);
                        ResultActions resultActions = mockMvc.perform(delete("/pte/{id}", savedPTE.getPteId()));
                        resultActions.andExpect(status().isOk()).andDo(print());
                    }
                    @Test
                    public void givenPTEId_whenDeletePTE_thenReturn404() throws Exception {
                        Timestamp now = new Timestamp(System.currentTimeMillis());
                        Integer invalidPTEId = 1000;
                        PTE pte = PTE.builder().build();
                        pte = PTE.builder()
                                .pteId(10)
                                .trainingId(10)
                                .email("abc@def.com")
                                .appStatus("Submitted")
                                .firstName("John")
                                .lastName("Doe")
                                .position("Accountant")
                                .zipCode(46075)
                                .dataApplied(now.toString())
                                .lastUpdate(now)
                                .build();
                        PTE savedPTE = pteRepository.save(pte);
                        ResultActions resultActions = mockMvc.perform(delete("/pte/{id}", invalidPTEId));
                        resultActions.andExpect(status().isNotFound()).andDo(print());
                    }
}
