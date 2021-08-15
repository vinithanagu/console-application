package com.example.consoleapplication;

import lombok.extern.log4j.Log4j2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@Log4j2
public class ConsoleApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConsoleApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		log.info("EXECUTING : command line runner");
	}

}
