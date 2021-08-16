package com.example.consoleapplication;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.example.consoleapplication.model.AddressEntity;
import com.example.consoleapplication.model.PersonEntity;
import com.example.consoleapplication.service.AddressService;
import com.example.consoleapplication.service.PersonalService;

@SpringBootApplication
@AllArgsConstructor
@Log4j2
public class ConsoleApplication implements CommandLineRunner {

	private final PersonalService personalService;

	private final AddressService addressService;

	public static void main(String[] args) {
		SpringApplication.run(ConsoleApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		log.info("EXECUTING : command line runner");

		//add a person
		PersonEntity personEntity = PersonEntity.builder().firstName("sponge").secondName("bob").build();
		System.out.println(personalService.addPerson(personEntity));

		//edit a person
		System.out.println(personalService.updatePerson(1L,"spongebob", "squarepants"));

		//Count Number of Persons
		System.out.println("number of person" + ":" + personalService.countPersons());

		//Add Address
		AddressEntity addressEntity = AddressEntity.builder().street("TempleStreet").state("dublin").city("dublin").build();
		System.out.println(addressService.addAddress(addressEntity));

		//Edit Address (street, city, state, postalCode)
		AddressEntity editAddressEntity = AddressEntity.builder().street("GraftonStreet").build();
		System.out.println(addressService.updateAddress(1L,editAddressEntity));

		//Add Address to person
		PersonEntity personEntity1 = personalService.addAddressToPerson(1L,1L);
		System.out.println(personEntity1.getAddress());
	}

}
