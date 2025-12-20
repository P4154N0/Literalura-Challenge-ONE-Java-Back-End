package com.aluracursos.literalura;

import com.aluracursos.literalura.principal.App;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})  //  Para trabajar sin haber configurado una base de datos.
@SpringBootApplication
public class LiteraluraHpgApplication implements CommandLineRunner {
	//  Para poder hacer uso de nuestra Línea de Comando vamos a tener que hacer uso de una interfase llamada
	//  CommandLineRunner, lo que va a permitir realizar acciones cuando inicie nuestra aplicación.
	//  Ej: mensajes de consola, cargar una base de datos, ect.

	@Autowired
	private LibroRepository repository;

	@Autowired
	private AutorRepository autorRepository; // <--- Agregamos esta línea

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraHpgApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//System.out.println("- ¡Hola \uD83C\uDF0E desde Spring!");

		App app = new App(repository, autorRepository);
		app.aplicacionRun();

		// Iconos de resultados.
		//📘
		//📙
		//📕
	}
}
