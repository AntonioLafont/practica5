package edu.comillas.icai.gitt.pat.spring.practica5.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;

@Entity
@Table(name = "linea_carrito")
public class LineaCarrito {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLineaCarrito;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "idCarrito", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Carrito carrito;
    
    @Column(nullable = false)
    private Long idArticulo;

    @Column(nullable = false)
    private Integer numeroUnidades;

    @Column(nullable = false)
    private Double precioUnitario;

    @Column(nullable = false)
    private Double costeLinea;

    public LineaCarrito() {
    }

    public LineaCarrito(Carrito carrito, Long idArticulo, Integer numeroUnidades, Double precioUnitario) {
        this.carrito = carrito;
        this.idArticulo = idArticulo;
        this.numeroUnidades = numeroUnidades;
        this.precioUnitario = precioUnitario;
        this.costeLinea = precioUnitario * numeroUnidades;
    }

    public Long getIdLineaCarrito() {
        return idLineaCarrito;
    }

    public void setIdLineaCarrito(Long idLineaCarrito) {
        this.idLineaCarrito = idLineaCarrito;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Long getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Long idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Integer getNumeroUnidades() {
        return numeroUnidades;
    }

    public void setNumeroUnidades(Integer numeroUnidades) {
        this.numeroUnidades = numeroUnidades;
        // Recalcular costeLinea cuando cambia el número de unidades
        if (this.precioUnitario != null) {
            this.costeLinea = this.precioUnitario * numeroUnidades;
        }
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
        // Recalcular costeLinea cuando cambia el precio unitario
        if (this.numeroUnidades != null) {
            this.costeLinea = precioUnitario * this.numeroUnidades;
        }
    }

    public Double getCosteLinea() {
        return costeLinea;
    }

    public void setCosteLinea(Double costeLinea) {
        this.costeLinea = costeLinea;
    }
}
