package com.example.consoleapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.consoleapplication.model.AddressEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long>{
}
