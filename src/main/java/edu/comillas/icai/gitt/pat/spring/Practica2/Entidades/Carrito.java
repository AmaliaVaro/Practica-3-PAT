package edu.comillas.icai.gitt.pat.spring.Practica2.Entidades;


import jakarta.persistence.*;

@Entity
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long IdCarrito;

    @Column(nullable = false, unique = true)
    public Long IdUsuario;

    @Column(nullable = false, unique = true)
    public String correoUsuario;



    @Column(nullable = false)
    public Double precioTotal;

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }
}
