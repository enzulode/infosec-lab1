package com.enzulode.persistence.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IsNotPersistedException extends RuntimeException {

  public IsNotPersistedException(String message) {
    super(message);
  }

  public IsNotPersistedException(String message, Throwable cause) {
    super(message, cause);
  }
}
