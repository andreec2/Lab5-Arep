package com.example.Controller;


import com.example.Service.ServicePropiety;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/propiety")
public class PropiedadController {

    private final ServicePropiety service;

    public PropiedadController(ServicePropiety service) {
        this.service = service;
    }

    @GetMapping
    public String listarPropiedades() {
        return "propiety";  // Solo devuelve la plantilla, sin datos
    }
}
