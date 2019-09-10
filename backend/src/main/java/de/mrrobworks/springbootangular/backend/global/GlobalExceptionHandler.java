package de.mrrobworks.springbootangular.backend.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler
  public void handleException(Exception e) throws Exception {
    log.error(e.getMessage(), e);
    throw e;
  }
}
