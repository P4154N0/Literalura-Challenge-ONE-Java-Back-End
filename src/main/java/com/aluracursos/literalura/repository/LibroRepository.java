package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Idioma;
import com.aluracursos.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    // Usamos el nombre del enum como String para que coincida con el array de Postgres
    @Query("SELECT l FROM Libro l JOIN l.idiomas i WHERE CAST(i AS string) = :idioma")
    List<Libro> encontrarLibrosPorIdioma(String idioma);

    Optional<Libro> findByTituloContainsIgnoreCase(String titulo);
}