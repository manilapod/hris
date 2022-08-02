package org.goup10.hris.controllers;

import org.goup10.hris.entities.PTE;
import org.goup10.hris.repositories.PTERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/pte")
public class PTEController {
    @Autowired
    private PTERepository pteRepository;

    public PTEController(PTERepository pteRepository) {
        this.pteRepository = pteRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PTE createPTE(@RequestBody PTE pte) {
        return pteRepository.save(pte);
    }

    @GetMapping
    @ResponseBody
    public Iterable<PTE> getAllPTE() {
        return pteRepository.findAll();
    }

    @GetMapping(path="{id}")
    @ResponseBody
    public ResponseEntity<PTE> getPTEById(@PathVariable Integer id) {
        return pteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<PTE> updatePTE(@PathVariable Integer id, @RequestBody PTE pte){
        return pteRepository.findById(id)
                .map(savedPTE -> {
                    PTE updatedPTE = pteRepository.save(pte);
                    return new ResponseEntity<>(updatedPTE, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePayRoll(@PathVariable("id") Integer id){
        try {
            pteRepository.deleteById(id);
            return new ResponseEntity<String>("PTE deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("PTE Not Found!", HttpStatus.NOT_FOUND);
        }
    }
}