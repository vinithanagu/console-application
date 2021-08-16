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
import com.example.consoleapplication.service.PersonService;

@SpringBootApplication
@AllArgsConstructor
@Log4j2
public class ConsoleApplication implements CommandLineRunner {

	private final PersonService personService;

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
		System.out.println(personService.addPerson(personEntity));

		//edit a person
		System.out.println(personService.updatePerson(1L,"spongebob", "squarepants"));

		//Count Number of Persons
		System.out.println("number of person" + ":" + personService.countPersons());

		//Add Address
		AddressEntity addressEntity = AddressEntity.builder().street("TempleStreet").state("dublin").city("dublin").build();
		System.out.println(addressService.addAddress(addressEntity));

		//Edit Address (street, city, state, postalCode)
		AddressEntity editAddressEntity = AddressEntity.builder().street("GraftonStreet").build();
		System.out.println(addressService.updateAddress(1L,editAddressEntity));

		//Add Address to person
		PersonEntity personEntity1 = personService.addAddressToPerson(1L,1L);
		System.out.println(personEntity1.getAddress());

		//delete a person
		System.out.println(personService.deletePerson(1L));

		//delete a Address(should throw address not found exception since we are using cascade on join)
//		System.out.println(addressService.deleteAddress(1L));
	}

}
