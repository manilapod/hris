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
    public void givenTrainingObject_whenCreateTraining_thenReturnSavedTraining() throws Exception {
        Training training = Training.builder().build();

        ResultActions resultActions = mockMvc.perform(post("/training")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(training)));
        resultActions.andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.trainingId", is(training.getTrainingId())));
    }
    @Test
    public void givenListOfTraining_whenGetAllTraining_thenReturnTrainingList() throws Exception {
        List<Training> trainingList = new ArrayList<>();
        Training training1 = Training.builder().build();
        Training training2 = Training.builder().build();

        trainingList.add(training1);
        trainingList.add(training2);
        trainingRepository.saveAll(trainingList);

        ResultActions resultActions = mockMvc.perform(get("/training"));
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(trainingList)));
    }
    @Test
    public void givenTrainingId_whenGetTrainingById_thenReturnTrainingObject() throws Exception{
        Training training = Training.builder().build();
        Training savedTraining = trainingRepository.save(training);
        ResultActions resultActions = mockMvc.perform(get("/training/{id}", training.getTrainingId()));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.trainingId", is(training.getTrainingId())));

    }
    @Test
    public void givenInvalidTrainingId_whenGetTrainingById_thenReturnEmpty() throws Exception{
        Integer invalidTrainingId = 1000;
        Training training = Training.builder().build();
        Training savedTraining = trainingRepository.save(training);
        ResultActions resultActions = mockMvc.perform(put("/training/{id}", invalidTrainingId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedTraining)));
        resultActions.andExpect(status().isNotFound()).andDo(print());
    }
    @Test
    public void givenUpdatedTraining_whenUpdateTraining_thenReturnUpdateTrainingObject() throws Exception{
        Training training = Training.builder().build();
        Training savedTraining = trainingRepository.save(training);

        savedTraining.getTrainee_name();

        ResultActions resultActions = mockMvc.perform(put("/training/{id}", training.getTrainingId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedTraining)));

        resultActions.andExpect(status().isOk()).andDo(print())
                .andExpect(jsonPath("$.trainee_name", is(training.getTrainee_name())));

    }
    @Test
    public void givenUpdatedTraining_whenUpdateTraining_thenReturn404() throws Exception{
        Integer invalidTrainingId = 1000;
        Training training = Training.builder().build();
        Training savedTraining = trainingRepository.save(training);
        ResultActions resultActions = mockMvc.perform(put("/training/{id}", invalidTrainingId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedTraining)));
        resultActions.andExpect(status().isNotFound()).andDo(print());
    }
    @Test
    public void givenTrainingId_whenDeleteTraining_thenReturn200() throws Exception{
        Training training = Training.builder().build();
        Training savedTraining = trainingRepository.save(training);

        ResultActions resultActions = mockMvc.perform(delete("/training/{id}", savedTraining.getTrainingId()));
        resultActions.andExpect(status().isOk()).andDo(print());
    }
    @Test
    public void givenTrainingId_whenDeleteTraining_thenReturn404() throws Exception{
        Integer invalidTrainingId = 1000;
        Training training = Training.builder().build();
        Training savedTraining = trainingRepository.save(training);

        ResultActions resultActions = mockMvc.perform(delete("/training/{id}", invalidTrainingId));
        resultActions.andExpect(status().isNotFound()).andDo(print());
    }
}
