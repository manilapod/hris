package org.goup10.hris.controllers;


import org.goup10.hris.entities.Training;
import org.goup10.hris.repositories.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/training")
public class TrainingController {@Autowired
private TrainingRepository trainingRepository;

    public TrainingController(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Training createTraining(@RequestBody Training training) {
        return trainingRepository.save(training);
    }

    @GetMapping
    @ResponseBody
    public Iterable<Training> getAllTraining() {
        return trainingRepository.findAll();
    }

    @GetMapping(path="{id}")
    @ResponseBody
    public ResponseEntity<Training> getTrainingById(@PathVariable Integer id) {
        return trainingRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Training> updateTraining(@PathVariable Integer id, @RequestBody Training training){
        return trainingRepository.findById(id)
                .map(savedTraining -> {
                    Training updatedTraining = trainingRepository.save(training);
                    return new ResponseEntity<>(updatedTraining, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTraining(@PathVariable("id") Integer id){
        try {
            trainingRepository.deleteById(id);
            return new ResponseEntity<String>("Training deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Training Not Found!", HttpStatus.NOT_FOUND);
        }
    }
}