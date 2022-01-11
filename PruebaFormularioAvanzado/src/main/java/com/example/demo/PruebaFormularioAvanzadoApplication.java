package com.example.demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Empleado;
import com.example.demo.repository.EmpleadoRepository;

@SpringBootApplication
public class PruebaFormularioAvanzadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaFormularioAvanzadoApplication.class, args);
	}
	
	@Bean
	CommandLineRunner initData(EmpleadoRepository repositorio) {
		return (args) -> {
			
//			  Empleado emp1 = new Empleado("José Pérez", "jose.perez@dominio.es", "656777888"); 
//			  Empleado emp2 = new Empleado("María Sánchez", "maria.sanchez@dominio.es", "656111222"); 
//			  Empleado emp3 = new Empleado("Miguel Rodríguez", "miguel.rodriguez@dominio.es", "656444555");
//			  
//			  repositorio.save(emp1); repositorio.save(emp2); repositorio.save(emp3);
//			  repositorio.findAll().forEach(System.out::println);
			 
			
			
//			repositorio.saveAll(
//					Arrays.asList(new Empleado(1, "José Pérez", "jose.perez@dominio.es", "656777888"),
//							new Empleado(2, "María Sánchez", "maria.sanchez@dominio.es", "656111222"),
//							new Empleado(3, "Miguel Rodríguez", "miguel.rodriguez@dominio.es", "656444555")
//							)
//					);
			
			List<String> nombres = Arrays.asList("Lucas", "Hugo", "Martín", "Daniel", "Pablo", "Alejandro", "Mateo",
					"Adrián", "Álvaro", "Manuel", "Leo", "David", "Mario", "Diego", "Javier", "Luis", "Marcos", "Juan",
					"José", "Gonzalo", "Lucía", "Sofía", "María", "Martina", "Paula", "Julia", "Daniela", "Valeria",
					"Alba", "Emma", "Carla", "Sara", "Noa", "Carmen", "Claudia", "Valentina", "Alma", "Ana", "Luisa",
					"Marta");

			List<String> apellidos = Arrays.asList("García", "González", "López", "Rodríguez", "Martínez", "Sánchez",
					"Pérez", "Gómez", "Martín", "Saez", "Velasco", "Moya", "Soler", "Parra", "Bravo", "Rojas", "Romero",
					"Sosa", "Torres", "Álvarez", "Flores", "Molina", "Ortiz", "Silva", "Torres");


			
			Collections.shuffle(nombres);

			repositorio.saveAll(IntStream.rangeClosed(1, nombres.size()).mapToObj((i) -> {
				String nombre = nombres.get(i-1);
				String apellido1 = apellidos.get(ThreadLocalRandom.current().nextInt(apellidos.size()));
				String apellido2 = apellidos.get(ThreadLocalRandom.current().nextInt(apellidos.size()));
				return new Empleado(String.format("%s %s %s", nombre, apellido1, apellido2), 
						String.format("%s.%s@dominio.es", nombre.toLowerCase(), apellido1.toLowerCase()), "954000000");
			}).collect(Collectors.toList()));

			
			
		};
	}

}
