package com.example.demo;

import com.example.Model.Propiety;
import com.example.Repository.PropietyRepository;
import com.example.Service.ServicePropiety;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ServicePropietyTest {

    @Mock
    private PropietyRepository repository;

    @InjectMocks
    private ServicePropiety servicePropiety;

    private Propiety propiety;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        propiety = new Propiety("Calle Principal 123", 100000, "150m²", "Casa de 3 habitaciones");
        propiety.setIdPropiety(1L);
    }

    @Test
    public void getAllPropietiesTest() {
        // Given
        List<Propiety> propieties = Arrays.asList(
                propiety,
                new Propiety("Avenida Central 456", 150000, "200m²", "Apartamento de lujo")
        );
        when(repository.findAll()).thenReturn(propieties);

        // When
        List<Propiety> result = servicePropiety.getAllPropieties();

        // Then
        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void getPropietyByIdTest() {
        // Given
        when(repository.findById(1L)).thenReturn(Optional.of(propiety));

        // When
        Optional<Propiety> result = servicePropiety.getPropietieById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals("Calle Principal 123", result.get().getDireccion());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    public void getPropietyByIdNotFoundTest() {
        // Given
        when(repository.findById(999L)).thenReturn(Optional.empty());

        // When
        Optional<Propiety> result = servicePropiety.getPropietieById(999L);

        // Then
        assertFalse(result.isPresent());
        verify(repository, times(1)).findById(999L);
    }

    @Test
    public void savePropietyTest() {
        // Given
        Propiety newPropiety = new Propiety("Nueva Calle 789", 120000, "180m²", "Casa nueva");
        when(repository.save(any(Propiety.class))).thenReturn(newPropiety);

        // When
        Propiety savedPropiety = servicePropiety.savePropiety(newPropiety);

        // Then
        assertNotNull(savedPropiety);
        assertEquals("Nueva Calle 789", savedPropiety.getDireccion());
        verify(repository, times(1)).save(any(Propiety.class));
    }

    @Test
    public void deleteByIdTest() {
        // Given
        doNothing().when(repository).deleteById(1L);

        // When
        servicePropiety.deleteById(1L);

        // Then
        verify(repository, times(1)).deleteById(1L);
    }
}
