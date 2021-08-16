package com.example.consoleapplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "address")
public class AddressEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "street_name")
  private String street;

  @Column(name = "city")
  private String city;

  @Column(name = "state")
  private String state;

  @Column(name = "postal_code")
  private String postalCode;

  @ManyToOne
  private PersonEntity personEntity;
}
