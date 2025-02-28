package com.example.Model;
import jakarta.persistence.*;
import jakarta.persistence.Id;

@Entity
@Table(name = "propiedades") // Nombre de la tabla en MySQL
public class Propiety {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPropiety;

    @Column(nullable = false)
    private String direccion;

    private int precio;
    private String tamano;
    private String descripcion;

    protected Propiety() {}

    public Propiety(String direccion, int precio, String tamano, String descripcion) {
        this.direccion = direccion;
        this.precio = precio;
        this.tamano = tamano;
        this.descripcion = descripcion;
    }

    public Long getIdPropiety() {
        return idPropiety;
    }

    public void setIdPropiety(Long idPropiety) {
        this.idPropiety = idPropiety;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
