package org.goup10.hris.controllers;

import org.goup10.hris.entities.Employee;
import org.goup10.hris.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeesRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeesRepository.save(employee);
    }

    @GetMapping
    @ResponseBody
    public Iterable<Employee> getAllEmployees() {
        return employeesRepository.findAll();
    }

    @GetMapping(path="{id}")
    @ResponseBody
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        return employeesRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee){
        return employeesRepository.findById(id)
                .map(savedEmployee -> {
                    Employee updatedEmployee = employeesRepository.save(employee);
                    return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Integer id){
        try {
            employeesRepository.deleteById(id);
            return new ResponseEntity<String>("Employee deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Employee Not Found!", HttpStatus.NOT_FOUND);
        }
    }
}
