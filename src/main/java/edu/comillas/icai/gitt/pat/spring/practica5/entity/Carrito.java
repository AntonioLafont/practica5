package edu.comillas.icai.gitt.pat.spring.practica5.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "carrito")
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarrito;
    
    @Column(nullable = false)
    private Long idUsuario;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private Double totalPrecio = 0.0;

    public Carrito() {
    }

    public Carrito(Long idUsuario, String correo) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.totalPrecio = 0.0;
    }

    public Long getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Long idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Double getTotalPrecio() {
        return totalPrecio;
    }

    public void setTotalPrecio(Double totalPrecio) {
        this.totalPrecio = totalPrecio;
    }
}
