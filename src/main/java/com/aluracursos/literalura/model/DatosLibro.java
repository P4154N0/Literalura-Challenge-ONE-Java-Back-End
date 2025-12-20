package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Representa los datos básicos de una serie obtenidos desde una API externa (como OMDb).
 * Se utiliza un 'record' para garantizar la inmutabilidad y reducir el código repetitivo.
 * * @JsonIgnoreProperties(ignoreUnknown = true) es fundamental aquí: indica a Jackson que ignore
 * cualquier campo que venga en el JSON de la API pero que no esté definido en este record,
 * evitando errores de "propiedad desconocida".
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(

        @JsonAlias("id") Integer id,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor> autores,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Double numeroDeDescargas
) {
}

//  DATA JSON -> de gutendex para Book->

//{
//        "id": <number of Project Gutenberg ID>,
//        "title": <string>,
//        "subjects": <array of strings>,
//        "authors": <array of Persons>,
//        "summaries": <array of strings>,
//        "translators": <array of Persons>,
//        "bookshelves": <array of strings>,
//        "languages": <array of strings>,
//        "copyright": <boolean or null>,
//        "media_type": <string>,
//        "formats": <Format>,
//        "download_count": <number>
//}

//----- LIBRO -----
//Titulo:
//Autor: Apellido, 1er. Nombre
//Idioma: en
//Número de descargas: 123456789
//-----------------

