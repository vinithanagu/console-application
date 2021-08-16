package com.example.consoleapplication.service;

import static org.mockito.ArgumentMatchers.any;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.consoleapplication.exceptions.PersonNotFoundException;
import com.example.consoleapplication.model.AddressEntity;
import com.example.consoleapplication.model.PersonEntity;
import com.example.consoleapplication.repository.PersonRepository;
import com.example.consoleapplication.service.impl.AddressServiceImpl;
import com.example.consoleapplication.service.impl.PersonalServiceImpl;

@DisplayName("PersonService Unit Tests")
@ExtendWith(MockitoExtension.class)
public class PersonServiceImpTest {

  @Mock
  private PersonRepository personRepository;

  @Mock
  private AddressServiceImpl addressService;

  @InjectMocks
  private PersonalServiceImpl personalService;


  @Test
  public void addPersonTest(){
    var entity = PersonEntity.builder()
        .firstName("test")
        .secondName("example")
        .build();

    var entity1 =PersonEntity.builder()
        .id(1)
        .firstName("test")
        .secondName("example")
        .build();

    Mockito.when(personRepository.save(entity)).thenReturn(entity1);

    var result = personalService.addPerson(entity);

    Assertions.assertEquals(entity.getFirstName(), result.getFirstName());
    Assertions.assertEquals(entity.getSecondName(), result.getSecondName());
    Assertions.assertEquals(1, result.getId());
    Mockito.verify(personRepository, Mockito.times(1)).save(entity);
  }

  @Test
  public void updatePersonTest(){
    var entity = PersonEntity.builder()
        .firstName("test")
        .secondName("example")
        .build();

    var entity1 =PersonEntity.builder()
        .firstName("test")
        .secondName("anotherExample")
        .build();

    Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(entity));
    Mockito.when(personRepository.save(entity1)).thenReturn(entity1);

    var result = personalService.updatePerson(1L, "test", "anotherExample");

    Assertions.assertEquals("anotherExample", result.getSecondName());
  }

  @Test
  public void updatePersonTest_NotFoundPerson(){
    Mockito.when(personRepository.findById(1L)).thenReturn(Optional.empty());

    Assertions.assertThrows(PersonNotFoundException.class, () -> personalService.updatePerson(1L, "test",
        "anotherExample"));

    Mockito.verify(personRepository, Mockito.times(0)).save(any());
  }

  @Test
  public void deletePersonTest(){
    var entity = PersonEntity.builder()
        .id(1)
        .firstName("test")
        .secondName("example")
        .build();

    Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(entity));

    var result = personalService.deletePerson(1L);

    Assertions.assertEquals(result.getId(), entity.getId());
    Mockito.verify(personRepository, Mockito.times(1)).delete(entity);
  }

  @Test
  public void deletePersonTest_NotFoundPerson(){
    Mockito.when(personRepository.findById(1L)).thenReturn(Optional.empty());

    Assertions.assertThrows(PersonNotFoundException.class, () -> personalService.deletePerson(1L));

    Mockito.verify(personRepository, Mockito.times(0)).delete(any());
  }

  @Test
  public void countPersonTest(){
    Mockito.when(personRepository.count()).thenReturn(4L);
    Long result = personalService.countPersons();
    Assertions.assertEquals(4L, result);
    Mockito.verify(personRepository, Mockito.times(1)).count();
  }

  @Test
  public void getPersonByIdTest(){
    var entity = PersonEntity.builder()
        .id(1)
        .firstName("test")
        .secondName("example")
        .build();
    Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(entity));
    PersonEntity result = personalService.getPerson(1L);
    Assertions.assertEquals(result.getFirstName(), entity.getFirstName());
    Assertions.assertEquals(result.getSecondName(), entity.getSecondName());
    Mockito.verify(personRepository, Mockito.times(1)).findById(1L);
  }

  @Test
  public void getPersonByIdTest_NotFoundPerson(){
    Mockito.when(personRepository.findById(1L)).thenReturn(Optional.empty());

    Assertions.assertThrows(PersonNotFoundException.class, () -> personalService.getPerson(1L));
  }

  @Test
  public void getAllPersonTest(){
    var entity = PersonEntity.builder()
        .id(1)
        .firstName("test")
        .secondName("example")
        .build();
    Mockito.when(personRepository.findAll()).thenReturn(Collections.singletonList(entity));

    List<PersonEntity> personEntities = personalService.getAllPerson();
    Assertions.assertEquals(1, personEntities.size());
    Assertions.assertEquals(entity, personEntities.get(0));
  }

  @Test
  public void addAddressToPersonTest(){
    var entity = PersonEntity.builder()
        .firstName("test")
        .secondName("example")
        .build();

    var addressEntity = AddressEntity.builder()
        .street("street")
        .city("dublin")
        .build();

    Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(entity));
    Mockito.when(addressService.getAddress(1L)).thenReturn(addressEntity);

    PersonEntity personEntity = personalService.addAddressToPerson(1L, 1L);
    Assertions.assertEquals(personEntity.getAddress(), addressEntity);
  }

}
