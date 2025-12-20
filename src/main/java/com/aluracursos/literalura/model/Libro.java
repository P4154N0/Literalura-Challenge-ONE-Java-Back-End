package com.aluracursos.literalura.model;

import jakarta.persistence.*;
import java.util.ArrayList; // Ahora se usa obligatoriamente en el constructor
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLibro;

    private Integer id;

    @Column(unique = true)
    private String titulo;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autores;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Idioma> idiomas;

    private Double numeroDeDescargas;

    public Libro() {}

    public Libro(DatosLibro datosLibro) {
        this.id = datosLibro.id();
        this.titulo = datosLibro.titulo();

        // Mapeo de autores usando ArrayList
        this.autores = datosLibro.autores().stream()
                .map(a -> {
                    Autor autor = new Autor(a);
                    autor.setLibro(this);
                    return autor;
                })
                .collect(Collectors.toCollection(ArrayList::new));

        // Mapeo de idiomas usando ArrayList (Esto evita que queden como [])
        if (datosLibro.idiomas() != null && !datosLibro.idiomas().isEmpty()) {
            this.idiomas = datosLibro.idiomas().stream()
                    .map(i -> {
                        try {
                            return Idioma.fromString(i);
                        } catch (IllegalArgumentException e) {
                            return Idioma.DESCONOCIDO;
                        }
                    })
                    .collect(Collectors.toCollection(ArrayList::new));
        } else {
            this.idiomas = new ArrayList<>();
        }

        this.numeroDeDescargas = datosLibro.numeroDeDescargas() != null ? datosLibro.numeroDeDescargas() : 0.0;
    }

    // --- GETTERS Y SETTERS ---

    public Long getIdLibro() { return idLibro; }
    public void setIdLibro(Long idLibro) { this.idLibro = idLibro; }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public List<Autor> getAutores() { return autores; }
    public void setAutores(List<Autor> autores) {
        if (autores != null) {
            autores.forEach(a -> a.setLibro(this));
        }
        this.autores = autores;
    }

    public List<Idioma> getIdiomas() { return idiomas; }
    public void setIdiomas(List<Idioma> idiomas) { this.idiomas = idiomas; }

    public Double getNumeroDeDescargas() { return numeroDeDescargas; }
    public void setNumeroDeDescargas(Double numeroDeDescargas) { this.numeroDeDescargas = numeroDeDescargas; }

    @Override
    public String toString() {
        return String.format("""
                | --- LIBRO ENCONTRADO ---
                | Título: %s
                | Idiomas en BDD: %s
                | Descargas: %.1f
                | ---------------------------""",
                titulo, idiomas, numeroDeDescargas);
    }
}