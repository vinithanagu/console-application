package com.example.consoleapplication.exceptions;

import java.text.MessageFormat;

public class PersonNotFoundException extends RuntimeException{

  public PersonNotFoundException(final Long id){
    super(MessageFormat.format("Could not find person with id: {0}", id));
  }

}
