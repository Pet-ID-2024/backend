package com.petid.api.exception;

import com.petid.domain.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(
            {
                    MemberDataNotFoundException.class,
                    PetDataException.class,
                    HospitalDataNotFoundException.class,
                    LocationDataNotFoundException.class,
                    PetDataException.class
            }
    )
    public ResponseEntity<ExceptionResponse> handleMemberNotFoundException(
            HttpServletRequest request,
            MemberDataNotFoundException e
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ExceptionResponse.of(
                        404,
                        e.getMessage(),
                        request.getRequestURI()
                )
        );
    }

    @ExceptionHandler(UnSupportedPlatformException.class)
    public ResponseEntity<ExceptionResponse> handleUnSupportedPlatformException(
            HttpServletRequest request,
            UnSupportedPlatformException e
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ExceptionResponse.of(
                        400,
                        e.getMessage(),
                        request.getRequestURI()
                )
        );
    }
}
