package com.example.consoleapplication.exceptions;

import java.text.MessageFormat;

public class AddressNotFoundException extends RuntimeException{

  public AddressNotFoundException(final Long id){
    super(MessageFormat.format("Could not find Address with id: {0}", id));
  }

}
