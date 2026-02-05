
# 📖 LiterAlura - Gestión Bibliográfica Personalizada

---

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Insomnia](https://img.shields.io/badge/Insomnia-4000BF?style=for-the-badge&logo=Insomnia&logoColor=white)
![Trello](https://img.shields.io/badge/Trello-%23026AA7.svg?style=for-the-badge&logo=Trello&logoColor=white)

---

**LiterAlura** es una robusta aplicación de consola desarrollada en Java con el framework **Spring Boot**. El proyecto permite interactuar con la API **Gutendex** para buscar libros, procesar datos bibliográficos en formato JSON y almacenarlos de forma eficiente en una base de datos relacional. Destaca por una interfaz de usuario artesanal con banners personalizados y un flujo de navegación intuitivo.

---

## 🖥️ Interfaz del Programa

---

El sistema cuenta con un menú visualmente atractivo diseñado con caracteres ASCII y validaciones en tiempo real para asegurar una experiencia de usuario sólida.

### 📍 Menú Principal
*(Aquí puedes insertar una captura de pantalla de tu banner de Literalura y las opciones del menú)*

![Menú Principal de la Aplicación](/img/menu.png)


---

## 🚀 Funcionalidades Destacadas

---

### 1. 🔍 Búsqueda Inteligente (Web)
Conexión directa con la API Gutendex. El sistema busca y filtra el resultado más relevante.
* **Validación de Unicidad:** Implementación de lógica para verificar si el libro ya existe en la base de datos local, evitando duplicados mediante restricciones de SQL (`UNIQUE`) y validaciones en Java con `Optional`.

### 📍 Ejemplo de Búsqueda y Validación
*(Sugerencia: Imagen mostrando la búsqueda de un libro y el banner de aviso de que ya existe.)*

![Validación de Duplicados](/img/validacion_de_duplicados.png)

### 📍 Ejemplo de Listar Libros Registrados
*(Sugerencia: Imagen que muestra la lista de todos los libros que ya tenés guardados en tu base de datos local.)*

![Validación de Duplicados](/img/libros_guardados.png)

### 📍 Ejemplo de Listar Autores Registrados
*(Sugerencia: Imagen que muestra la lista de todos los autores que ya tenés guardados en tu base de datos local.)*

![Validación de Duplicados](/img/autores_guardados.png)

### 📍 Ejemplo de Listar Autores Registrados de una época dada. (Año)
*(Sugerencia: Imagen que muestra la lista de todos los autores que ya tenés guardados en tu base de datos local que pertenecieron a una época dada. (Año))*

![Validación de Duplicados](/img/autores_de_epoca.png)

### 📍 Ejemplo de Listar Libros por idioma. Ej: (es, en, fr, pt)
*(Sugerencia: Imagen que muestra la lista de todos los autores que ya tenés guardados en tu base de datos local que están escritos en un determinado idioma.)*

![Validación de Duplicados](/img/libro_por_idioma.png)

### 📍 Ejemplo de Listar 10 Libros más descargados. (Web)
*(Sugerencia: Imagen que muestra la lista de los 10 libros más descargados. (Consulta Web))*

![Validación de Duplicados](/img/top_10_libros.png)

### 📍 Ejemplo de Generar estadísticas. (Web)
*(Sugerencia: Imagen que muestra la lista de los 10 libros más descargados. (Consulta Web))*

![Validación de Duplicados](/img/estadisticas.png)


### 2. 📚 Biblioteca Personalizada
Listado completo de libros con detalles de descargas y autores vinculados.
* **Filtro por Idioma:** Consulta rápida mediante códigos internacionales (`es`, `en`, `fr`, `pt`).

### 3. ✍️ Gestión de Autores
Base de datos de escritores con información sobre su año de nacimiento y fallecimiento.
* **Filtro Cronológico:** Permite descubrir qué autores estaban vivos en un año específico mediante consultas JPA personalizadas.

---

## 🛠️ Tecnologías y Conceptos Aplicados

---

* **Java 17/21:** Uso de **Records** para un mapeo de datos inmutable y **Streams** para el procesamiento eficiente de colecciones.
* **Spring Boot 3.x:** Motor principal para la inyección de dependencias y gestión del ciclo de vida.
* **Spring Data JPA:** Implementación de **Derived Queries** para búsquedas personalizadas (ej. `findByTituloContainsIgnoreCase`).
* **PostgreSQL:** Persistencia relacional con relaciones `@OneToMany` y colecciones `@ElementCollection`.
* **Jackson Library:** Mapeo de JSON a Objetos Java mediante anotaciones `@JsonAlias` y `@JsonIgnoreProperties`.

---

## 📊 Estructura de la Base de Datos

---

La base de datos `literalura_hpg` está normalizada para asegurar la integridad de la información y la correcta relación entre libros y autores.

### 📍 Vista en pgAdmin
*(Aquí puedes colocar la captura de pantalla de tus tablas en PostgreSQL)*

![Estructura de Tablas en PostgreSQL](/img/estructura_tablas.png)

![Estructura de Tablas en PostgreSQL](/img/tabla_libros.png)

![Estructura de Tablas en PostgreSQL](/img/tabla_autores.png)

---

## ⚙️ Configuración y Ejecución

---

### 1. Requisitos
* JDK 17 o superior.
* Maven 3.x.
* PostgreSQL 15+.



### 2. Configuración de Base de Datos
Configura tus credenciales en `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura_hpg
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```

### 3. Instalación y Uso

```bash
# Clonar el repositorio
git clone [https://github.com/P4154N0/Literalura-Challenge-ONE-Java-Back-End.git](https://github.com/P4154N0/Literalura-Challenge-ONE-Java-Back-End.git)

# Entrar al directorio
cd literalura

# Ejecutar la aplicación
mvn spring-boot:run
```

---

## 🤠 Sobre el Autor
Este proyecto fue desarrollado con mucha calma por un **P4154N0**, cebando varios mates 🧉 y al ritmo de música country 🎵. Como apasionado de la programación en Argentina 🇦🇷, busqué reflejar un equilibrio real entre la solidez técnica del Backend y un diseño estético artesanal en la consola.


