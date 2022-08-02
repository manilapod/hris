package org.goup10.hris.controllers;

import org.goup10.hris.entities.Insurance;
import org.goup10.hris.repositories.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="/insurance")
public class InsuranceController {
    @Autowired
    private InsuranceRepository insuranceRepository;

    public InsuranceController(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Insurance createInsurance(@RequestBody Insurance insurance) {
        return insuranceRepository.save(insurance);
    }

    @GetMapping
    @ResponseBody
    public Iterable<Insurance> getAllInsurance() {
        return insuranceRepository.findAll();
    }

    @GetMapping(path="{id}")
    @ResponseBody
    public ResponseEntity<Insurance> getInsuranceById(@PathVariable Integer id) {
        return insuranceRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Insurance> updateInsurance(@PathVariable Integer id, @RequestBody Insurance insurance){
        return insuranceRepository.findById(id)
                .map(savedInsurance -> {
                    Insurance updatedInsurance = insuranceRepository.save(insurance);
                    return new ResponseEntity<>(updatedInsurance, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteInsurance(@PathVariable("id") Integer id){
        try {
            insuranceRepository.deleteById(id);
            return new ResponseEntity<String>("Insurance deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Insurance Not Found!", HttpStatus.NOT_FOUND);
        }
    }
}