package ar.edu.utn.frc.backend.grupo114;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        // Esta es la línea que ARRANCA Spring Boot,
        // conecta la BDD y crea las tablas.
        SpringApplication.run(Main.class, args);
    }

}