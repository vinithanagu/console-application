package com.example.consoleapplication.exceptions;

import java.text.MessageFormat;

public class AddressIsAlreadyAssignedException extends RuntimeException{
    public AddressIsAlreadyAssignedException(final Long addressId, final Long personId){
        super(MessageFormat.format("Address: {0} is already assigned to person: {1}", addressId, personId));
    }
}
