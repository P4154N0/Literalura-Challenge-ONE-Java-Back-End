package com.aluracursos.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // JPA necesita un ID propio para la tabla de autores

    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeDefuncion;

    @ManyToOne
    @JoinColumn(name = "libro_id_libro") // Esto fuerza a JPA a usar exactamente esa columna para el ID
    private Libro libro;

    public Autor() {}

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.fechaDeNacimiento = datosAutor.fechaDeNacimiento();
        this.fechaDeDefuncion = datosAutor.fechaDeDefuncion();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Integer getFechaDeDefuncion() {
        return fechaDeDefuncion;
    }

    public void setFechaDeDefuncion(Integer fechaDeDefuncion) {
        this.fechaDeDefuncion = fechaDeDefuncion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {

        this.libro = libro;
    }

    @Override
    public String toString() {
        return String.format("""
                         ---------- AUTOR ----------
                        | - Apellido, Nombre: %s
                        | - Nacimiento: %s
                        | - Fallecimiento: %s
                        | ---------------------------""",
                        nombre,
                        (fechaDeNacimiento != null ? fechaDeNacimiento : "N/A"),
                        (fechaDeDefuncion != null ? fechaDeDefuncion : "Vivo/Desconocido"));
    }
}
