package com.example.consoleapplication;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.example.consoleapplication.model.AddressEntity;
import com.example.consoleapplication.model.PersonEntity;
import com.example.consoleapplication.service.AddressService;
import com.example.consoleapplication.service.PersonalService;

@SpringBootApplication
@Log4j2
public class ConsoleApplication implements CommandLineRunner {

	@Autowired
	private PersonalService personalService;

	@Autowired
	private AddressService addressService;

	public static void main(String[] args) {
		SpringApplication.run(ConsoleApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		log.info("EXECUTING : command line runner");
		PersonEntity personEntity = PersonEntity.builder().firstName("sai").secondName("ram").build();

		log.info(personalService.addPerson(personEntity));

		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setCity("sligo");
		log.info(addressService.addAddress(addressEntity));
		log.info(addressService.deleteAddress(1l));
	}

}
