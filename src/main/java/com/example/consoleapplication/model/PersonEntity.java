package com.example.consoleapplication.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "person")
public class PersonEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "second_name")
  private String secondName;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "person_id")
  private final Set<AddressEntity> address = new HashSet<>();

  public void addAddress(AddressEntity addressEntity){
    address.add(addressEntity);
  }

  public void removeAddress(AddressEntity addressEntity){
    address.remove(addressEntity);
  }

}
