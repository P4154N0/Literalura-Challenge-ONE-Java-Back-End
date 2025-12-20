package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Datos(
        @JsonAlias("results") List<DatosLibro> resultados
) {
}

//  DATA JSON -> de gutendex para Results->

//"count": 77309,
//        "next": "https://gutendex.com/books/?page=2",
//        "previous": null,
//        "results": [resultados]