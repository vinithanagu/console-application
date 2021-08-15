package com.example.consoleapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.consoleapplication.model.PersonEntity;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long>{

}
