package com.example.consoleapplication.service;

import com.example.consoleapplication.model.AddressEntity;

/**
 * The interface Address service.
 */
public interface AddressService {

    /**
     * Add address address entity.
     *
     * @param addressEntity the address entity
     * @return the address entity
     */
    AddressEntity addAddress(AddressEntity addressEntity);

    /**
     * Delete address address entity.
     *
     * @param id the id
     * @return the address entity
     */
    AddressEntity deleteAddress(Long id);

    /**
     * Update address address entity.
     *
     * @param id            the id
     * @param addressEntity the address entity
     * @return the address entity
     */
    AddressEntity updateAddress(Long id, AddressEntity addressEntity);

    /**
     * Gets address.
     *
     * @param id the id
     * @return the address
     */
    AddressEntity getAddress(Long id);
}
