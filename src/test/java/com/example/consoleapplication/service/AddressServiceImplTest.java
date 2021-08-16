package com.example.consoleapplication.service;

import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.consoleapplication.exceptions.AddressNotFoundException;
import com.example.consoleapplication.model.AddressEntity;
import com.example.consoleapplication.repository.AddressRepository;
import com.example.consoleapplication.service.impl.AddressServiceImpl;

@DisplayName("PersonService Unit Tests")
@ExtendWith(MockitoExtension.class)
public class AddressServiceImplTest {

  @Mock
  private AddressRepository addressRepository;

  @InjectMocks
  private AddressServiceImpl addressService;


  @Test
  public void addAddressTest(){
    var addressEntity = AddressEntity.builder()
        .street("street")
        .city("dublin")
        .build();

    var addressEntity1 = AddressEntity.builder()
        .id(1)
        .street("street")
        .city("dublin")
        .build();

    Mockito.when(addressRepository.save(addressEntity)).thenReturn(addressEntity1);

    var result = addressService.addAddress(addressEntity);

    Assertions.assertEquals(addressEntity.getStreet(), result.getStreet());
    Assertions.assertEquals(addressEntity.getCity(), result.getCity());
    Assertions.assertEquals(1, result.getId());
    Mockito.verify(addressRepository, Mockito.times(1)).save(addressEntity);
  }

  @Test
  public void updateAddressTest(){
    var addressEntity = AddressEntity.builder()
        .street("street")
        .city("dublin")
        .build();

    var addressEntity1 = AddressEntity.builder()
        .street("street")
        .city("dublin")
        .postalCode("TEST")
        .build();

    Mockito.when(addressRepository.findById(1L)).thenReturn(Optional.of(addressEntity));
    Mockito.when(addressRepository.save(addressEntity1)).thenReturn(addressEntity1);

    var result = addressService.updateAddress(1L, addressEntity1);

    Assertions.assertEquals(addressEntity1.getPostalCode(), result.getPostalCode());
  }

  @Test
  public void updateAddressTest_NotFoundAddress(){
    Mockito.when(addressRepository.findById(1L)).thenReturn(Optional.empty());

    Assertions.assertThrows(AddressNotFoundException.class, () -> addressService.updateAddress(1L, null));

    Mockito.verify(addressRepository, Mockito.times(0)).save(any());
  }

  @Test
  public void deleteAddressTest(){
    var addressEntity = AddressEntity.builder()
        .id(1)
        .street("street")
        .city("dublin")
        .build();

    Mockito.when(addressRepository.findById(1L)).thenReturn(Optional.of(addressEntity));

    var result = addressService.deleteAddress(1L);

    Assertions.assertEquals(result.getId(), addressEntity.getId());
    Mockito.verify(addressRepository, Mockito.times(1)).delete(any());
  }

  @Test
  public void deleteAddressTest_NotFoundAddress(){
    Mockito.when(addressRepository.findById(1L)).thenReturn(Optional.empty());

    Assertions.assertThrows(AddressNotFoundException.class, () -> addressService.deleteAddress(1L));

    Mockito.verify(addressRepository, Mockito.times(0)).delete(any());
  }

  @Test
  public void getAddressByIdTest(){
    var addressEntity = AddressEntity.builder()
        .id(1)
        .street("street")
        .city("dublin")
        .build();
    Mockito.when(addressRepository.findById(1L)).thenReturn(Optional.of(addressEntity));
    AddressEntity result = addressService.getAddress(1L);
    Assertions.assertEquals(result.getStreet(), addressEntity.getStreet());
    Assertions.assertEquals(result.getCity(), addressEntity.getCity());
    Mockito.verify(addressRepository, Mockito.times(1)).findById(1L);
  }

  @Test
  public void getAddressByIdTest_NotFoundAddress(){
    Mockito.when(addressRepository.findById(1L)).thenReturn(Optional.empty());

    Assertions.assertThrows(AddressNotFoundException.class, () -> addressService.getAddress(1L));
  }
}
