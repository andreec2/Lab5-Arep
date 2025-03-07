package com.example.demo;

import com.example.Model.Propiety;
import com.example.Service.ServicePropiety;
import com.example.Controller.PropietyController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PropietyControllerTest {

    @Mock
    private ServicePropiety servicePropiety;

    @InjectMocks
    private PropietyController propietyController;

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
        when(servicePropiety.getAllPropieties()).thenReturn(propieties);

        // When
        List<Propiety> result = propietyController.getAllPropieties();

        // Then
        assertEquals(2, result.size());
        verify(servicePropiety, times(1)).getAllPropieties();
    }

    @Test
    public void getPropietyByIdTest() {
        // Given
        when(servicePropiety.getPropietieById(1L)).thenReturn(Optional.of(propiety));

        // When
        Optional<Propiety> result = propietyController.getPropietieById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals("Calle Principal 123", result.get().getDireccion());
        verify(servicePropiety, times(1)).getPropietieById(1L);
    }

    @Test
    public void createPropietyTest() {
        // Given
        Propiety newPropiety = new Propiety("Nueva Calle 789", 120000, "180m²", "Casa nueva");
        when(servicePropiety.savePropiety(any(Propiety.class))).thenReturn(newPropiety);

        // When
        ResponseEntity<Propiety> response = propietyController.createPropiety(newPropiety);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Nueva Calle 789", response.getBody().getDireccion());
        verify(servicePropiety, times(1)).savePropiety(any(Propiety.class));
    }

    @Test
    public void updatePropietyTest() {
        // Given
        Propiety updatedPropiety = new Propiety("Calle Actualizada 123", 110000, "155m²", "Casa actualizada");
        updatedPropiety.setIdPropiety(1L);

        when(servicePropiety.getPropietieById(1L)).thenReturn(Optional.of(propiety));
        when(servicePropiety.savePropiety(any(Propiety.class))).thenReturn(updatedPropiety);

        // When
        ResponseEntity<Propiety> response = propietyController.updatePropiety(1L, updatedPropiety);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Calle Actualizada 123", response.getBody().getDireccion());
        verify(servicePropiety, times(1)).getPropietieById(1L);
        verify(servicePropiety, times(1)).savePropiety(any(Propiety.class));
    }

    @Test
    public void updatePropietyNotFoundTest() {
        // Given
        when(servicePropiety.getPropietieById(999L)).thenReturn(Optional.empty());

        // When
        ResponseEntity<Propiety> response = propietyController.updatePropiety(999L, propiety);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(servicePropiety, times(1)).getPropietieById(999L);
        verify(servicePropiety, never()).savePropiety(any(Propiety.class));
    }

    @Test
    public void deletePropietyTest() {
        // Given
        doNothing().when(servicePropiety).deleteById(1L);

        // When
        ResponseEntity<Void> response = propietyController.deletePropiety(1L);

        // Then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(servicePropiety, times(1)).deleteById(1L);
    }
}
