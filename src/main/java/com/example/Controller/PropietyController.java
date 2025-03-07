package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import com.example.Service.ServicePropiety;
import com.example.Repository.PropietyRepository;
import com.example.Model.Propiety;

@RestController
@RequestMapping("/api/propiety")
public class PropietyController {


    private final ServicePropiety servicePropiety;

    @Autowired
    public PropietyController(ServicePropiety servicePropiety) {
        this.servicePropiety = servicePropiety;
    }

    @GetMapping
    public List<Propiety> getAllPropieties() {
        return servicePropiety.getAllPropieties();
    }

    @GetMapping("/{id}")
    public Optional<Propiety> getPropietieById(@PathVariable long id) {
        return servicePropiety.getPropietieById(id);
    }

    @PostMapping
    public ResponseEntity<Propiety> createPropiety(@RequestBody Propiety propiety) {
        Propiety savedPropiety = servicePropiety.savePropiety(propiety);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPropiety);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Propiety> updatePropiety(@PathVariable long id, @RequestBody Propiety propiety) {
        return servicePropiety.getPropietieById(id)
                .map(existingPropiety -> {
                    propiety.setIdPropiety(id); // Aseguramos que el ID sea el correcto
                    Propiety updatedPropiety = servicePropiety.savePropiety(propiety);
                    return ResponseEntity.ok(updatedPropiety);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePropiety(@PathVariable long id) {
        servicePropiety.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}