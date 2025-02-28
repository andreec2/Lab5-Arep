package com.example.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Model.Propiety;

public interface PropietyRepository extends JpaRepository<Propiety, Long> {
}
