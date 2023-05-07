package com.example.javaclasses;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class DentalClinic {

	public static void main(String[] args) {

		SpringApplication.run(DentalClinic.class, args);

	}

	/*@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			System.out.println("Done---");
		};
	}*/

}
