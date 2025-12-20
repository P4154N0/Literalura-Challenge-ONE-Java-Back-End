package com.aluracursos.literalura.model;

public enum Idioma {

    ESPANOL("es", "Español"),
    INGLES("en", "Inglés"),
    FRANCES("fr", "Francés"),
    PORTUGUES("pt", "Portugués"),
    ALEMAN("de", "Alemán"),
    ITALIANO("it", "Italiano"),
    CHINO("zh", "Chino"),
    JAPONES("ja", "Japonés"),
    RUSO("ru", "Ruso"),
    LATIN("la", "Latín"),
    HOLANDES("nl", "Holandés"),
    DESCONOCIDO("unknown", "Desconocido");

    private String lenguajeGutendex;
    private String lenguajeEspanol;

    // Constructor del Enum
    Idioma(String lenguajeGutendex, String lenguajeEspanol) {
        this.lenguajeGutendex = lenguajeGutendex;
        this.lenguajeEspanol = lenguajeEspanol;
    }

    // Método para convertir el String de la API (es, en) al Enum (ESPANOL, INGLES)
    public static Idioma fromString(String text) {
        for (Idioma idioma : Idioma.values()) {
            // Comparamos contra "es", "en", etc. que es lo que el usuario ingresa
            if (idioma.lenguajeGutendex.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ningún idioma encontrado para: " + text);
    }

    // Método para mostrar el nombre bonito en el menú o detalles
    public String getLenguajeEspanol() {
        return lenguajeEspanol;
    }
}
