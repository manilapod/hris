package org.goup10.hris.controllers;


import org.goup10.hris.entities.Retirement;
import org.goup10.hris.repositories.RetirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/retirement")
public class RetirementController {
    @Autowired
    private RetirementRepository retirementRepository;

    public RetirementController(RetirementRepository retirementRepository) {
        this.retirementRepository = retirementRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Retirement createRetirement(@RequestBody Retirement retirement) {
        return retirementRepository.save(retirement);
    }

    @GetMapping
    @ResponseBody
    public Iterable<Retirement> getAllRetirement() {
        return retirementRepository.findAll();
    }

    @GetMapping(path="{id}")
    @ResponseBody
    public ResponseEntity<Retirement> getRetirementById(@PathVariable Integer id) {
        return retirementRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Retirement> updateRetirement(@PathVariable Integer id, @RequestBody Retirement retirement){
        return retirementRepository.findById(id)
                .map(savedRetirement -> {
                    Retirement updatedRetirement = retirementRepository.save(retirement);
                    return new ResponseEntity<>(updatedRetirement, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRetirement(@PathVariable("id") Integer id){
        try {
            retirementRepository.deleteById(id);
            return new ResponseEntity<String>("Retirement deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Retirement Not Found!", HttpStatus.NOT_FOUND);
        }
    }
}