package com.aluracursos.literalura.service;

/*
  Interfaz que define el contrato para la conversión de datos.
  El objetivo es desacoplar la lógica de la biblioteca externa (como Jackson)
  del resto de la aplicación.
 */

public interface IConvierteDatos {

    /*
      Convierte una cadena de texto en formato JSON al tipo de objeto especificado.
      * @param <T>   Tipo genérico: permite que el método devuelva cualquier clase
      (DatosSerie, DatosEpisodio, etc.) sin repetir código.
      @param json  La cadena de texto cruda que llega desde la API.
      @param clase La clase de destino a la que queremos convertir el JSON
      (ej. DatosSerie.class).
      @return      Una instancia de la clase T con los datos del JSON mapeados.
     */
    <T> T obtenerDatos(String json, Class<T> clase);
}