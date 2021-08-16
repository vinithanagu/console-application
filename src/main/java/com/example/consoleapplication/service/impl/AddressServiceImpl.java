package com.example.consoleapplication.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.consoleapplication.exceptions.AddressNotFoundException;
import com.example.consoleapplication.model.AddressEntity;
import com.example.consoleapplication.repository.AddressRepository;
import com.example.consoleapplication.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

  private final AddressRepository addressRepository;

  public AddressServiceImpl(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }

  @Override
  public AddressEntity addAddress(AddressEntity addressEntity) {
    return addressRepository.save(addressEntity);
  }

  @Override
  public AddressEntity deleteAddress(Long id) {
    AddressEntity address = getAddress(id);
    addressRepository.delete(address);
    return address;
  }

  @Override
  @Transactional
  public AddressEntity updateAddress(Long id, AddressEntity addressEntity) {
    AddressEntity address = getAddress(id);
    address.setStreet(addressEntity.getStreet());
    address.setCity(addressEntity.getCity());
    address.setPostalCode(addressEntity.getPostalCode());
    address.setState(addressEntity.getState());
    return addressRepository.save(address);
  }

  @Override
  public AddressEntity getAddress(Long id){
    return addressRepository.findById(id).orElseThrow(() ->
        new AddressNotFoundException(id));
  }

}
