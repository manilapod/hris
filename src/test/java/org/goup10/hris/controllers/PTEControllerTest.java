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

import java.security.Timestamp;
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
    public void givenPTEObject_whenCreatePTE_thenReturnSavedPTE() throws Exception {
        PTE pte = PTE.builder()
                .pteId(1)
                .trainingId(1)
                .email("test@gmail.com")
                .app_status("Likey")
                .first_name("test")
                .last_name("Test")
                .position("Tester")
                .zip_code(208)
                .data_applied(String.valueOf(2015-2-25))
                //.last_update(new Timestamp()) need to be fix
                .build();

        ResultActions resultActions = mockMvc.perform(post("/pte")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pte)));
        resultActions.andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.pteId", is(pte.getPteId())))
                .andExpect(jsonPath("$.trainingId", is(pte.getTrainingId())))
                .andExpect(jsonPath("$.email", is(pte.getEmail())))
                .andExpect(jsonPath("$.app_status", is(pte.getApp_status())))
                .andExpect(jsonPath("$.first_name", is(pte.getFirst_name())))
                .andExpect(jsonPath("$.last_name", is(pte.getLast_name())))
                .andExpect(jsonPath("$.position", is(pte.getPosition())))
                .andExpect(jsonPath("$.zip_code", is(pte.getZip_code())))
                .andExpect(jsonPath("$.data_applied", is(pte.getData_applied())))
                .andExpect(jsonPath("$.last_update", is(pte.getLast_update())));
    }
    @Test
    public void givenListOfPTE_whenGetAllPTE_thenReturnPTEList() throws Exception {
        List<PTE> pteList = new ArrayList<>();
        PTE pte1 = PTE.builder()
                .pteId(1)
                .trainingId(1)
                .email("test@gmail.com")
                .app_status("Likey")
                .first_name("test")
                .last_name("Test")
                .position("Tester")
                .zip_code(208)
                .data_applied(String.valueOf(2015-2-25))
                //.last_update(new Timestamp()) need to be fix
                .build();
        PTE pte2 = PTE.builder()
                .pteId(1)
                .trainingId(1)
                .email("test@gmail.com")
                .app_status("Likey")
                .first_name("test")
                .last_name("Test")
                .position("Tester")
                .zip_code(208)
                .data_applied(String.valueOf(2015-2-25))
                //.last_update(new Timestamp()) need to be fix
                .build();

        pteList.add(pte1);
        pteList.add(pte2);
        pteRepository.saveAll(pteList);

        ResultActions resultActions = mockMvc.perform(get("/pte"));
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(pteList)));
    }
    @Test
    public void givenPTEId_whenGetPTEById_thenReturnPTEObject() throws Exception{
        PTE pte = PTE.builder()
                .pteId(1)
                .trainingId(1)
                .email("test@gmail.com")
                .app_status("Likey")
                .first_name("test")
                .last_name("Test")
                .position("Tester")
                .zip_code(208)
                .data_applied(String.valueOf(2015-2-25))
                //.last_update(new Timestamp()) need to be fix
                .build();
        PTE savedPTE = pteRepository.save(pte);
        ResultActions resultActions = mockMvc.perform(get("/pte/{id}", savedPTE.getPteId()));
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.first_name", is(pte.getFirst_name())))
                .andExpect(jsonPath("$.last_name", is(pte.getLast_name())))
                .andExpect(jsonPath("$.position", is(pte.getPosition())));

    }
    @Test
    public void givenInvalidPTEId_whenGetPTEById_thenReturnEmpty() throws Exception{
        Integer invalidPTEId = 1000;
        PTE pte = PTE.builder()
                .pteId(1)
                .trainingId(1)
                .email("test@gmail.com")
                .app_status("Likey")
                .first_name("test")
                .last_name("Test")
                .position("Tester")
                .zip_code(208)
                .data_applied(String.valueOf(2015-2-25))
                //.last_update(new Timestamp()) need to be fix
                .build();
        PTE savedPTE = pteRepository.save(pte);
        ResultActions resultActions = mockMvc.perform(put("/pte/{id}", invalidPTEId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedPTE)));
        resultActions.andExpect(status().isNotFound()).andDo(print());
    }
    @Test
    public void givenUpdatedPTE_whenUpdatePTE_thenReturnUpdatePTEObject() throws Exception{
        PTE pte = PTE.builder()
                .pteId(1)
                .trainingId(1)
                .email("test@gmail.com")
                .app_status("Likey")
                .first_name("test")
                .last_name("Test")
                .position("Tester")
                .zip_code(208)
                .data_applied(String.valueOf(2015-2-25))
                //.last_update(new Timestamp()) need to be fix
                .build();
        PTE savedPTE = pteRepository.save(pte);

        savedPTE.getFirst_name();

        ResultActions resultActions = mockMvc.perform(put("/pte/{id}", savedPTE.getPteId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedPTE)));

        resultActions.andExpect(status().isOk()).andDo(print())
                .andExpect(jsonPath("$.first_name", is(pte.getFirst_name())));

    }
    @Test
    public void givenUpdatedPTE_whenUpdatePTE_thenReturn404() throws Exception{
        Integer invalidPTEId = 1000;
        PTE pte = PTE.builder()
                .pteId(1)
                .trainingId(1)
                .email("test@gmail.com")
                .app_status("Likey")
                .first_name("test")
                .last_name("Test")
                .position("Tester")
                .zip_code(208)
                .data_applied(String.valueOf(2015-2-25))
                //.last_update(new Timestamp()) need to be fix
                .build();
        PTE savedPTE = pteRepository.save(pte);
        ResultActions resultActions = mockMvc.perform(put("/pte/{id}", invalidPTEId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedPTE)));
        resultActions.andExpect(status().isNotFound()).andDo(print());
    }
    @Test
    public void givenPTEId_whenDeletePTE_thenReturn200() throws Exception{
        PTE pte = PTE.builder()
                .pteId(1)
                .trainingId(1)
                .email("test@gmail.com")
                .app_status("Likey")
                .first_name("test")
                .last_name("Test")
                .position("Tester")
                .zip_code(208)
                .data_applied(String.valueOf(2015-2-25))
                //.last_update(new Timestamp()) need to be fix
                .build();
        PTE savedPTE = pteRepository.save(pte);
        ResultActions resultActions = mockMvc.perform(delete("/pte/{id}", savedPTE.getPteId()));
        resultActions.andExpect(status().isOk()).andDo(print());
    }
    @Test
    public void givenPTEId_whenDeletePTE_thenReturn404() throws Exception{
        Integer invalidPTEId = 1000;
        PTE pte = PTE.builder()
                .pteId(1)
                .trainingId(1)
                .email("test@gmail.com")
                .app_status("Likey")
                .first_name("test")
                .last_name("Test")
                .position("Tester")
                .zip_code(208)
                .data_applied(String.valueOf(2015-2-25))
                //.last_update(new Timestamp()) need to be fix
                .build();
        PTE savedPTE = pteRepository.save(pte);
        ResultActions resultActions = mockMvc.perform(delete("/pte/{id}", invalidPTEId));
        resultActions.andExpect(status().isNotFound()).andDo(print());
    }
}

