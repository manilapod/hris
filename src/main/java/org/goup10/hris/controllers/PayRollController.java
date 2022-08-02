package org.goup10.hris.controllers;

import org.goup10.hris.entities.Payroll;
import org.goup10.hris.repositories.EmployeeRepository;
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
    public Payroll createPayRoll(@RequestBody Payroll payroll) {
        return payrollRepository.save(payroll);
    }

    @GetMapping
    @ResponseBody
    public Iterable<Payroll> getAllPayRoll() {
        return payrollRepository.findAll();
    }

    @GetMapping(path = "{id}")
    @ResponseBody
    public ResponseEntity<Payroll> getPayRollById(@PathVariable Integer id) {
        return payrollRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Payroll> updatePayRoll(@PathVariable Integer id, @RequestBody Payroll payroll) {
        return payrollRepository.findById(id)
                .map(savedPayRoll -> {
                    Payroll updatedPayroll = payrollRepository.save(payroll);
                    return new ResponseEntity<>(updatedPayroll, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePayRoll(@PathVariable("id") Integer id) {
        try {
            payrollRepository.deleteById(id);
            return new ResponseEntity<String>("PayRoll deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("PayRoll Not Found!", HttpStatus.NOT_FOUND);
        }
    }
}