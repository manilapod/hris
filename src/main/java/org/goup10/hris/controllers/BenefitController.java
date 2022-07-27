package org.goup10.hris.controllers;

import org.goup10.hris.entities.Benefit;
import org.goup10.hris.repositories.BenefitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/benefit")
public class BenefitController {
    @Autowired
    private BenefitRepository benefitRepository;

    public BenefitController(BenefitRepository benefitRepository) {
        this.benefitRepository = benefitRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Benefit createBenefit(@RequestBody Benefit benefit) {
        return benefitRepository.save(benefit);
    }

    @GetMapping
    @ResponseBody
    public Iterable<Benefit> getAllBenefit() {
        return benefitRepository.findAll();
    }

    @GetMapping(path="{id}")
    @ResponseBody
    public ResponseEntity<Benefit> getBenefitById(@PathVariable Integer id) {
        return benefitRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Benefit> updateBenefit(@PathVariable Integer id, @RequestBody Benefit benefit){
        return benefitRepository.findById(id)
                .map(savedBenefit -> {
                    Benefit updatedBenefit = benefitRepository.save(benefit);
                    return new ResponseEntity<>(updatedBenefit, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBenefit(@PathVariable("id") Integer id){
        try {
            benefitRepository.deleteById(id);
            return new ResponseEntity<String>("Benefit deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Benefit Not Found!", HttpStatus.NOT_FOUND);
        }
    }
}