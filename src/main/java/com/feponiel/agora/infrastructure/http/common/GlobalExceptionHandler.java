package com.feponiel.agora.infrastructure.http.common;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.feponiel.agora.domain.forum.application.usecases.exceptions.MemberAlreadyExistsException;
import com.feponiel.agora.infrastructure.http.dtos.ErrorResponse;
import com.feponiel.agora.infrastructure.http.dtos.FieldValidationError;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
    List<FieldError> fieldErrors = exception.getFieldErrors();

    var errorList = fieldErrors
      .stream()
      .map(fieldError -> new FieldValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
      .collect(Collectors.toList());

    ErrorResponse error = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation error", errorList);

    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
  }

  @ExceptionHandler(MemberAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleMemberAlreadyExistsException(MemberAlreadyExistsException exception) {
    List<FieldValidationError> fieldValidationError = List.of(new FieldValidationError(exception.getIdentifier(), "A member with this same " + exception.getIdentifier() + " already exists."));

    ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT.value(), exception.getMessage(), fieldValidationError);

    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }
}
