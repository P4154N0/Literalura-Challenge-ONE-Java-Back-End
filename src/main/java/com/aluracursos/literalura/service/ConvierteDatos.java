package com.aluracursos.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
  Clase responsable de la implementación real de la conversión de datos.
  Utiliza la biblioteca Jackson Databind para realizar el mapeo (binding)
  entre el texto JSON y los objetos Java.
 */
public class ConvierteDatos implements IConvierteDatos {

    // El ObjectMapper es el motor de Jackson. Se define como atributo para
    // evitar crear una nueva instancia pesada en cada conversión.
    private final ObjectMapper objectMapper = new ObjectMapper();

    /*
      Implementación del método genérico de la interfaz.
      * @param json  Texto recibido de la API.
      @param clase El modelo (Record o Clase) al que se desea mapear.
      @return      Un objeto del tipo T ya poblado con los datos.
      @throws RuntimeException Si ocurre un error de formato o mapeo en el JSON.
     */
    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            // readValue es el método de Jackson que "lee" el JSON y, usando
            // reflexión, instancia la clase destino y asigna los valores.
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            // Se relanza como RuntimeException para no ensuciar la firma del
            // método con "throws" y manejar el error de forma centralizada.
            throw new RuntimeException("Error fatal al convertir el JSON a la clase: "
                    + clase.getSimpleName(), e);
        }
    }
}