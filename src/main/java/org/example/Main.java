package org.example;

import org.example.ui.CommandLineUI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private final CommandLineUI ui;

    public Main(CommandLineUI ui) {
        this.ui = ui;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        ui.start();
    }
}
