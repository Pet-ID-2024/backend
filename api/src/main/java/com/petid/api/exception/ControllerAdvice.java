package com.petid.api.exception;

import com.petid.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(
            {
                    MemberDataNotFoundException.class,
                    PetDataNotFoundException.class,
                    HospitalDataNotFoundException.class,
                    LocationDataNotFoundException.class,
                    PetDataNotFoundException.class
            }
    )
    public ResponseEntity<ExceptionResponse> handleMemberNotFoundException(
            MemberDataNotFoundException e
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ExceptionResponse.of(
                        404,
                        e.getMessage()
                )
        );
    }

    @ExceptionHandler(UnSupportedPlatformException.class)
    public ResponseEntity<ExceptionResponse> handleUnSupportedPlatformException(
            UnSupportedPlatformException e
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ExceptionResponse.of(
                        400,
                        e.getMessage()
                )
        );
    }
}
