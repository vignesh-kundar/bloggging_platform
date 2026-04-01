package com.vignesh.spring_blog.exception;

import com.vignesh.spring_blog.dto.ErrorResponseDTO;
import jakarta.persistence.EntityExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.directory.InvalidAttributesException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponseDTO> handleNoSuchElementException(NoSuchElementException ex) {
        log.error("Error 404 : element Not Found");
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.NOT_FOUND , ex.getMessage(),  LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("Validation Failed : {}" , ex.getMessage());
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.BAD_REQUEST , ex.getMessage() , LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidAttributesException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidAttributesException(InvalidAttributesException ex) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.UNAUTHORIZED , ex.getMessage() , LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO , HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleEntityExistsException(EntityExistsException ex) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.CONFLICT , ex.getMessage() , LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO , HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex) {
        log.error("Error occured : {}" , ex.getMessage());
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR , ex.getMessage() , LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
    }

}