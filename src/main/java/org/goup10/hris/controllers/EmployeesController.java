package org.goup10.hris.controllers;

import org.goup10.hris.entities.Employees;
import org.goup10.hris.repositories.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/employees")
public class EmployeesController {
    @Autowired
    private EmployeesRepository employeesRepository;

    @GetMapping(path="/health")
    public @ResponseBody String checkHealth() {
        return "I am alive!";
    }

    @GetMapping(path="/")
    public @ResponseBody Iterable<Employees> getAllEmployees() {
        return employeesRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Optional<Employees> getAllEmployees(@PathVariable Integer id) {
        return employeesRepository.findById(id);
    }

    @PostMapping(path="/add")
    public @ResponseBody String saveEmployee(@RequestBody Employees employee) {
        employeesRepository.save(employee);
        return "Saved";
    }
}
