package com.example.demo;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Juego;
import com.example.demo.repository.JuegoRepository;

@SpringBootApplication
public class ProyectoRetrogamerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoRetrogamerApplication.class, args);
	}
	
	@Bean
	CommandLineRunner initData(JuegoRepository repositorio) {
		return (args) ->{
			repositorio.saveAll(
					Arrays.asList(new Juego("Alex Kidd in the Enchanted Castle", "Mega Drive", "1989", "SEGA", "Plataformas"),
							new Juego("Altered Beast", "Mega Drive", "1989", "SEGA", "Beat'em Up"),
							new Juego("Dynamite Duke", "Mega Drive", "1989", "Seibu Kaihatsu", "Shooter"),
							new Juego("Forgotten World", "Mega Drive", "1989", "Capcom", "Shoot'em Up"),
							new Juego("Ghouls'n Ghosts", "Mega Drive", "1989", "Capcom", "Plataformas"),
							new Juego("Golden Axe", "Mega Drive", "1989", "SEGA", "Beat'em Up"),
							new Juego("Last Battle", "Mega Drive", "1989", "SEGA", "Beat'em Up"),
							new Juego("Mystic Defender", "Mega Drive", "1989", "SEGA", "RPG"),
							new Juego("Phantasy Star II", "Mega Drive", "1989", "SEGA", "RPG"),
							new Juego("Rambo III", "Mega Drive", "1989", "SEGA", "Shoot'em Up"),
							new Juego("Space Harrier II", "Mega Drive", "1989", "SEGA", "Shoot'em Up"),
							new Juego("Super Thunder Blade", "Mega Drive", "1989", "SEGA", "Simulación aérea"),
							new Juego("Thunder Force II", "Mega Drive", "1989", "Technosoft", "Shoot'em Up"),
							new Juego("Tommy Lasorda Baseball", "Mega Drive", "1989", "SEGA", "Simulación deportiva"),
							new Juego("Truxton", "Mega Drive", "1989", "Toaplan", "Shoot'em Up"),
							new Juego("World Cup Italia '90", "Mega Drive", "1989", "SEGA", "Simulación deportiva"),
							new Juego("Zoom!", "Mega Drive", "1989", "Discovery", "Puzzle"),
							new Juego("After Burner II", "Mega Drive", "1990", "SEGA", "Simulación aérea"),
							new Juego("Arrow Flash", "Mega Drive", "1990", "Renovation", "Shoot'em Up"),
							new Juego("Batman", "Mega Drive", "1990", "Sunsoft", "Plataformas"),
							new Juego("Battle Squadron", "Mega Drive", "1990", "Electronic Arts", "Shoot'em Up")
							)
					);
			
			
		
	};

}
	
}
