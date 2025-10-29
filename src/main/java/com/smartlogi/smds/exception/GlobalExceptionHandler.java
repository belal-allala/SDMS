package com.smartlogi.smds.exception;

import com.smartlogi.smds.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                HttpStatus.NOT_FOUND.value(), // statusCode
                exception.getMessage(),       // message
                null,                         // details (pas utilisé ici, peut être null ou chaîne vide)
                LocalDateTime.now(),          // timestamp
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, WebRequest webRequest) {
        String errorMessage = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                HttpStatus.BAD_REQUEST.value(), // statusCode
                "Erreur de validation",            // message (un message général pour les erreurs de validation)
                errorMessage,                   // details (les erreurs de validation spécifiques)
                LocalDateTime.now(),            // timestamp
                webRequest.getDescription(false) // path
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception exception, WebRequest webRequest) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), // statusCode
                exception.getMessage(),                   // message
                null,                                     // details
                LocalDateTime.now(),                      // timestamp
                webRequest.getDescription(false)          // path
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
// Commit 36 on 2025-10-29 18:04:22
// Commit 33 on 2025-10-30 20:16:29
// Commit 106 on 2025-10-29 18:50:21
