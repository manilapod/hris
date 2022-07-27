package org.goup10.hris.controllers;

import org.goup10.hris.entities.PayRoll;
import org.goup10.hris.repositories.PayRollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/PayRoll")
public class PayRollController {
    @Autowired
    private PayRollRepository payrollRepository;

    public PayRollController(PayRollRepository payrollRepository) {
        this.payrollRepository = payrollRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PayRoll createPayRoll(@RequestBody PayRoll payroll) {
        return payrollRepository.save(payroll);
    }

    @GetMapping
    @ResponseBody
    public Iterable<PayRoll> getAllPayRoll() {
        return payrollRepository.findAll();
    }

    @GetMapping(path="{id}")
    @ResponseBody
    public ResponseEntity<PayRoll> getPayRollById(@PathVariable Integer id) {
        return payrollRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<PayRoll> updatePayRoll(@PathVariable Integer id, @RequestBody PayRoll payroll){
        return payrollRepository.findById(id)
                .map(savedPayRoll -> {
                    PayRoll updatedPayRoll = payrollRepository.save(payroll);
                    return new ResponseEntity<>(updatedPayRoll, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePayRoll(@PathVariable("id") Integer id){
        try {
            payrollRepository.deleteById(id);
            return new ResponseEntity<String>("PayRoll deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("PayRoll Not Found!", HttpStatus.NOT_FOUND);
        }
    }
}