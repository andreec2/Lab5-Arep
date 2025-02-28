package com.example.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.example.Repository.PropietyRepository;
import com.example.Model.Propiety;
@Service
public class ServicePropiety {

    private final PropietyRepository repository;

    @Autowired
    public ServicePropiety(PropietyRepository repository) {
        this.repository = repository;
    }

    public List<Propiety> getAllPropieties() {
        return repository.findAll();
    }

    public Optional<Propiety> getPropietieById(long id) {
        return repository.findById(id);
    }

    public Propiety savePropiety(Propiety propiety) {
        return repository.save(propiety);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
