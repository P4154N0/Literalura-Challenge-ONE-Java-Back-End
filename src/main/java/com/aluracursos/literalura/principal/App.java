package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.*;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.service.ConsumoAPI;
import com.aluracursos.literalura.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class App {

    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConvierteDatos convierteDatos = new ConvierteDatos();
    private final Scanner sc = new Scanner(System.in);
    private final String URL_BASE = "https://gutendex.com";
    private final String URL_BOOKS = "/books/";

    private LibroRepository repository;
    private AutorRepository autorRepository;

    private final String banner =("""
        
            |===================================================================================|
            |                                                                                   |                                                                             
            |   ██╗     ██╗████████╗███████╗██████╗  █████╗ ██╗     ██╗   ██╗██████╗  █████╗    |
            |   ██║     ██║╚══██╔══╝██╔════╝██╔══██╗██╔══██╗██║     ██║   ██║██╔══██╗██╔══██╗   |
            |   ██║     ██║   ██║   █████╗  ██████╔╝███████║██║     ██║   ██║██████╔╝███████║   |
            |   ██║     ██║   ██║   ██╔══╝  ██╔══██╗██╔══██║██║     ██║   ██║██╔══██╗██╔══██║   |
            |   ███████╗██║   ██║   ███████╗██║  ██║██║  ██║███████╗╚██████╔╝██║  ██║██║  ██║   |
            |   ╚══════╝╚═╝   ╚═╝   ╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝ ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝   |
            |                                                                                   |
            |===================================================================================|
            | Developed 💻 by a P4154N0 from 🇦🇷 who takes 🧉 and ❤️ country music 🤠 🎵🎵🎵 🇨🇦  |""");

    private final String menu = ("""
            |===================================================================================|
            |                       SISTEMA DE GESTIÓN BIBLIOGRÁFICA                            |
            |===================================================================================|
            | [1] -> Buscar libro por nombre. (Consulta en la Web) - (Luego registra los datos) |
            | [2] -> Listar Libros registrados. (BDD)                                           |
            | [3] -> Listar Autores registrados. (BDD)                                          |
            | [4] -> Listar Autores vivos en un determinado año. (BDD)                          |
            | [5] -> Listar Libros por idioma. (BDD)                                            |
            | [6] -> Listar el Top 10 de libros más descargados (Web).                          |
            | [7] -> Generar estadísticas de descargas (Web).                                   |
            |                                                                                   |
            | [0] -> SALIR DEL PROGRAMA.                                                        |
            |===================================================================================|""");

    public App(LibroRepository repository, AutorRepository autorRepository) {
        this.repository = repository;
        this.autorRepository = autorRepository;
    }

    public void aplicacionRun() {
        var opcion = -1;
        while (opcion != 0) {
            System.out.println(banner);
            System.out.println(menu);
            System.out.print("| ➤ Seleccione una opción: ");

            try {
                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    sc.nextLine();

                    switch (opcion) {
                        case 1 -> buscarLibroWeb();
                        case 2 -> listarLibros();
                        case 3 -> listarAutoresRegistrados();
                        case 4 -> buscarAutoresPorAnio();
                        case 5 -> listarLibrosPorIdioma();
                        case 6 -> top10LibrosMasDescargados();
                        case 7 -> obtenerEstadisticaDeLosLibros();
                        case 0 -> System.out.println("\n| - ¡Gracias por usar Literalura! Vuelve pronto.");
                        default -> System.out.println("\n| [!] Opción inválida.");
                    }

                    // AQUÍ: Si no eligió salir, esperamos el Enter antes de que el bucle vuelva a empezar
                    if (opcion != 0) {
                        presionarEnterParaContinuar();
                    }

                } else {
                    System.out.println("\n| [!] Error: Formato incorrecto.");
                    sc.next();
                    presionarEnterParaContinuar(); // También aquí para que lea el error
                }
            } catch (Exception e) {
                System.out.println("\n| [!] Error inesperado.");
                presionarEnterParaContinuar();
            }
        }
    }

    private void listarLibrosPorIdioma() {

        System.out.println("""
        |===================================================================================|
        |                🌐  FILTRANDO POR LA LENGUA DEL SABER                               |
        |===================================================================================|""");

        System.out.print("| ➤ Ingrese el código (es, en, fr, pt): ");
        var codigo = sc.nextLine().trim().toLowerCase();

        try {
            // 1. Convertimos "es" a ESPANOL (esto ya lo corregimos en tu Enum)
            Idioma idiomaBusqueda = Idioma.fromString(codigo);

            System.out.println("| - Buscando libros en: " + idiomaBusqueda.getLenguajeEspanol() + "...");

            // 2. Traemos TODOS los libros de la base de datos a la memoria de Java
            List<Libro> todosLosLibros = repository.findAll();

            // 3. Filtramos manualmente. Comparamos los nombres (Strings)
            // para que no importe si el objeto es distinto, solo importa el texto "ESPANOL"
            List<Libro> librosFiltrados = todosLosLibros.stream()
                    .filter(l -> l.getIdiomas().stream()
                            .anyMatch(i -> i.name().equalsIgnoreCase(idiomaBusqueda.name())))
                    .collect(Collectors.toList());

            if (librosFiltrados.isEmpty()) {
                System.out.println("\n| [!] No se hallaron libros registrados en " + idiomaBusqueda.getLenguajeEspanol());
                System.out.println("| - Tip: Asegúrate de haber buscado y guardado libros primero con la opción [1].");
            } else {
                System.out.println("\n--- RESULTADOS PARA " + idiomaBusqueda.getLenguajeEspanol().toUpperCase() + " ---");
                librosFiltrados.forEach(this::mostrarDetallesLibroEntidad);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\n| [!] Código de idioma no reconocido: " + codigo);
        }
    }

    // --- MÉTODOS DE SOPORTE ---

    public void buscarLibroWeb() {
        System.out.println("""
        |===================================================================================|
        |                🔍 BUSCANDO NUEVOS HORIZONTES LITERARIOS (WEB)                     |
        |===================================================================================|""");

        System.out.print("| - Ingrese el nombre del libro: ");
        String nombre = sc.nextLine();
        String json = consumoAPI.obtenerDatos(URL_BASE + URL_BOOKS + "?search=" + nombre.replace(" ", "+"));
        Datos datos = convierteDatos.obtenerDatos(json, Datos.class);

        Optional<DatosLibro> libroBuscado = datos.resultados().stream()
                .filter(l -> l.titulo().toLowerCase().contains(nombre.toLowerCase()))
                .findFirst();

        if (libroBuscado.isPresent()) {
            DatosLibro datosLibro = libroBuscado.get();

            // VALIDACIÓN: Verificamos si el título ya existe en la BDD antes de guardar
            // Nota: Asegúrate de tener este método en tu LibroRepository
            Optional<Libro> libroExistente = repository.findByTituloContainsIgnoreCase(datosLibro.titulo());

            if (libroExistente.isPresent()) {
                System.out.println("""
                |===================================================================================|
                |                ⚠️   AVISO: EL LIBRO YA EXISTE EN TU BIBLIOTECA                    |
                |===================================================================================|""");
                mostrarDetallesLibroEntidad(libroExistente.get());
            } else {
                Libro libro = new Libro(datosLibro);
                repository.save(libro);
                System.out.println("\n| - ¡Libro encontrado y guardado con éxito!");
                mostrarDetallesLibroEntidad(libro);
            }
        } else {
            System.out.println("\n| [!] Libro no encontrado en la web.");
        }
    }

    private void listarLibros() {
        List<Libro> libros = repository.findAll();

        System.out.println("""
            |===================================================================================|
            |                📚  BIBLIOTECA PERSONAL - LIBROS GUARDADOS                         |
            |===================================================================================|""");

        if (libros.isEmpty()) {
            System.out.println("\n| [!] No hay libros registrados en la base de datos.");
        } else {
            libros.forEach(l -> {
                System.out.println("\n| --------------- LIBRO ---------------");
                System.out.println("| - Título: " + l.getTitulo());
                //System.out.println("| - Autor: " + l.getAutores());
                System.out.println(l.getAutores());
                System.out.println("| - Idiomas en BDD: " + l.getIdiomas()); // <-- ESTO ES LO IMPORTANTE
                System.out.println("| - Número de descargas: " + l.getNumeroDeDescargas()); // <-- ESTO ES LO IMPORTANTE
                System.out.println("| -------------------------------------");
            });
            System.out.println("|===================================================================================|");
        }
    }

    private void listarAutoresRegistrados() {

        System.out.println("""
            |===================================================================================|
            |                ✍️   MAESTROS DE LA PLUMA REGISTRADOS                              |
            |===================================================================================|""");

        autorRepository.findAll().stream().distinct().forEach(System.out::println);
    }

    private void buscarAutoresPorAnio() {

        System.out.println("""
            |===================================================================================|
            |                ⏳  VIAJE EN EL TIEMPO: AUTORES DE ÉPOCA                           |
            |===================================================================================|""");

        System.out.print("| - Ingrese el año: ");
        try {
            int anio = Integer.parseInt(sc.nextLine());
            autorRepository.buscarAutoresVivosEnDeterminadoAnio(anio).forEach(System.out::println);
        } catch (NumberFormatException e) {
            System.out.println("| [!] Año inválido.");
        }
    }

    public void top10LibrosMasDescargados() {
        String json = consumoAPI.obtenerDatos(URL_BASE + URL_BOOKS);
        Datos datos = convierteDatos.obtenerDatos(json, Datos.class);

        System.out.println("""
        |===================================================================================|
        |                🏆  TOP 10: LAS JOYAS MÁS BUSCADAS EN EL MUNDO                     |
        |===================================================================================|""");

        datos.resultados().stream()
                .sorted(Comparator.comparing(DatosLibro::numeroDeDescargas).reversed())
                .limit(10)
                .forEach(l -> System.out.println("| " + l.titulo() + " [" + l.numeroDeDescargas().intValue() + "]"));
    }

    private void obtenerEstadisticaDeLosLibros() {

        System.out.println("""
            |===================================================================================|
            |                📊  ESTADÍSTICAS GENERALES DE DESCARGAS                            |
            |===================================================================================|""");

        String json = consumoAPI.obtenerDatos(URL_BASE + URL_BOOKS);
        Datos datos = convierteDatos.obtenerDatos(json, Datos.class);
        DoubleSummaryStatistics est = datos.resultados().stream()
                .collect(Collectors.summarizingDouble(DatosLibro::numeroDeDescargas));

        System.out.println("| - Mínimo de descargas: " + est.getMin());
        System.out.println("| - Media descargas: " + String.format("%.2f", est.getAverage()));
        System.out.println("| - Maximo de descargas: " + est.getMax());
        System.out.println("| - Muestra de libros: " + est.getCount());
        System.out.println("| - Suma de descargas: " + est.getSum());
    }

    private void mostrarDetallesLibroEntidad(Libro libro) {
        String autores = (libro.getAutores() == null) ? "Desconocido" :
                libro.getAutores().stream().map(Autor::getNombre).collect(Collectors.joining(", "));

        String idiomas = (libro.getIdiomas() == null) ? "No disponible" :
                libro.getIdiomas().stream().map(Idioma::getLenguajeEspanol).collect(Collectors.joining(", "));

        System.out.println("\n| ---------- LIBRO N° " + (libro.getIdLibro() != null ? libro.getIdLibro() : "---") + " ----------");
        System.out.println("| Título: " + libro.getTitulo().toUpperCase());
        System.out.println("| Autor: " + autores);
        System.out.println("| Idioma: " + idiomas);
        System.out.println("| Descargas: " + libro.getNumeroDeDescargas().intValue());
        System.out.println("| ------------------------------------");
    }

    private void presionarEnterParaContinuar() {
        System.out.println("\n| ➤ Presione ENTER para continuar...");
        sc.nextLine();
    }
}