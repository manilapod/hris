package org.goup10.hris.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.goup10.hris.entities.Training;
import org.goup10.hris.repositories.TrainingRepository;
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
public class TrainingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        trainingRepository.deleteAll();
    }
    @Test
    public void givenTrainingObject_whenCreateTraining_thenReturnSavedTraining() throws Exception{}
    @Test
    public void givenListOfTraining_whenGetAllTraining_thenReturnTrainingList() throws Exception {}
    @Test
    public void givenTrainingId_whenGetTrainingById_thenReturnTrainingObject() throws Exception{}
    @Test
    public void givenInvalidTrainingId_whenGetTrainingById_thenReturnEmpty() throws Exception{}
    @Test
    public void givenUpdatedTraining_whenUpdateTraining_thenReturnUpdateTrainingObject() throws Exception{}
    @Test
    public void givenUpdatedTraining_whenUpdateTraining_thenReturn404() throws Exception{}
    @Test
    public void givenTrainingId_whenDeleteTraining_thenReturn200() throws Exception{}
    @Test
    public void givenTrainingId_whenDeleteTraining_thenReturn404() throws Exception{}
}
