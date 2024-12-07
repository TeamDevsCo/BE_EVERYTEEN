package com.devsco.everyteen.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(BAD_REQUEST)
  public CustomErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    return CustomErrorResponse.from(ex);
  }

  @ExceptionHandler(HandlerMethodValidationException.class)
  @ResponseStatus(BAD_REQUEST)
  public CustomErrorResponse handleMethodArgumentNotValidException(HandlerMethodValidationException ex) {
    return CustomErrorResponse.from(ex);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(INTERNAL_SERVER_ERROR)
  public CustomErrorResponse handleRestException(Exception ex) {
    log.error(INTERNAL_SERVER_ERROR.getReasonPhrase(), ex);
    return new CustomErrorResponse(ProblemDetail.forStatus(INTERNAL_SERVER_ERROR));
  }
}
