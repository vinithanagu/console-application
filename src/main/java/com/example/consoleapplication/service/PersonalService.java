package com.example.consoleapplication.service;

import java.util.List;

import com.example.consoleapplication.model.PersonEntity;

/**
 * The interface Personal service.
 */
public interface PersonalService {

  /**
   * Add person person entity.
   *
   * @param personEntity the person entity
   * @return the person entity
   */
  PersonEntity addPerson(PersonEntity personEntity);

  /**
   * Update person person entity.
   *
   * @param id         the id
   * @param firstName  the first name
   * @param secondName the second name
   * @return the person entity
   */
  PersonEntity updatePerson(Long id, String firstName, String secondName);

  /**
   * Delete person person entity.
   *
   * @param id the id
   * @return the person entity
   */
  PersonEntity deletePerson(Long id);

  /**
   * Count persons long.
   *
   * @return the long
   */
  Long countPersons();

  /**
   * Gets all person.
   *
   * @return the all person
   */
  List<PersonEntity> getAllPerson();

  /**
   * Add address to person person entity.
   *
   * @param personId  the person id
   * @param addressId the address id
   * @return the person entity
   */
  PersonEntity addAddressToPerson(Long personId, Long addressId);

  /**
   * Remove address from person person entity.
   *
   * @param personId  the person id
   * @param addressId the address id
   * @return the person entity
   */
  PersonEntity removeAddressFromPerson(Long personId, Long addressId);

  /**
   * Gets person.
   *
   * @param id the id
   * @return the person
   */
  PersonEntity getPerson(Long id);

}
