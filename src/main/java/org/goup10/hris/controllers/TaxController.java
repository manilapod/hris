package org.goup10.hris.controllers;


import org.goup10.hris.entities.Tax;
import org.goup10.hris.repositories.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/tax")
public class TaxController {
    @Autowired
    private TaxRepository taxRepository;

    public TaxController(TaxRepository taxRepository) {
        this.taxRepository = taxRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tax createTax(@RequestBody Tax tax) {
        return taxRepository.save(tax);
    }

    @GetMapping
    @ResponseBody
    public Iterable<Tax> getAllTax() {
        return taxRepository.findAll();
    }

    @GetMapping(path="{id}")
    @ResponseBody
    public ResponseEntity<Tax> getTaxById(@PathVariable Integer id) {
        return taxRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Tax> updateTax(@PathVariable Integer id, @RequestBody Tax tax){
        return taxRepository.findById(id)
                .map(savedTax -> {
                    Tax updatedTax = taxRepository.save(tax);
                    return new ResponseEntity<>(updatedTax, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTax(@PathVariable("id") Integer id){
        try {
            taxRepository.deleteById(id);
            return new ResponseEntity<String>("Tax deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Tax Not Found!", HttpStatus.NOT_FOUND);
        }
    }
}