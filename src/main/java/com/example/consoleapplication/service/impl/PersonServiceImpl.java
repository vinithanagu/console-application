package com.example.consoleapplication.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.consoleapplication.exceptions.AddressIsAlreadyAssignedException;
import com.example.consoleapplication.exceptions.PersonNotFoundException;
import com.example.consoleapplication.model.AddressEntity;
import com.example.consoleapplication.model.PersonEntity;
import com.example.consoleapplication.repository.PersonRepository;
import com.example.consoleapplication.service.AddressService;
import com.example.consoleapplication.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

  private final PersonRepository personRepository;

  private final AddressService addressServiceImpl;

  public PersonServiceImpl(PersonRepository personRepository, AddressService addressService) {
    this.personRepository = personRepository;
    this.addressServiceImpl = addressService;
  }

  @Override
  public PersonEntity addPerson(PersonEntity personEntity) {
    var entity = PersonEntity.builder()
        .firstName(personEntity.getFirstName())
        .secondName(personEntity.getSecondName())
        .build();
    return personRepository.save(entity);
  }

  @Override
  @Transactional
  public PersonEntity updatePerson(Long id, String firstName, String secondName) {
    PersonEntity personEntity = getPerson(id);
    var entity = PersonEntity.builder()
        .firstName(firstName)
        .secondName(secondName)
        .id(personEntity.getId())
        .build();
    return personRepository.save(entity);
  }

  @Override
  public PersonEntity deletePerson(Long id) {
    PersonEntity personEntity = getPerson(id);
    personRepository.delete(personEntity);
    return personEntity;
  }

  @Override
  public Long countPersons() {
    return personRepository.count();
  }

  @Override
  public List<PersonEntity> getAllPerson() {
     return personRepository.findAll();
  }


  @Override
  @Transactional
  public PersonEntity addAddressToPerson(Long personId, Long addressId){
    PersonEntity personEntity = getPerson(personId);
    AddressEntity addressEntity = addressServiceImpl.getAddress(addressId);
    if(Objects.nonNull(addressEntity.getPersonEntity())){
      throw new AddressIsAlreadyAssignedException(addressId, addressEntity.getPersonEntity().getId());
    }
    personEntity.addAddress(addressEntity);
    addressEntity.setPersonEntity(personEntity);
    return personEntity;
  }

  @Override
  @Transactional
  public PersonEntity removeAddressFromPerson(Long personId, Long addressId){
    PersonEntity personEntity = getPerson(personId);
    AddressEntity addressEntity = addressServiceImpl.getAddress(addressId);
    personEntity.removeAddress(addressEntity);
    return personEntity;
  }


  @Override
  public PersonEntity getPerson(Long id){
    return personRepository.findById(id).orElseThrow(() ->
        new PersonNotFoundException(id));
  }
}
