package com.enzulode.persistence.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IsAlreadyPersistedException extends RuntimeException {

  public IsAlreadyPersistedException(String message) {
    super(message);
  }

  public IsAlreadyPersistedException(String message, Throwable cause) {
    super(message, cause);
  }
}
