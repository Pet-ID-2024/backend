package com.petid.api.exception;

import com.petid.auth.common.exception.CustomAuthException;
import com.petid.auth.common.exception.CustomAuthExceptionType;
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
                    LocationDataNotFoundException.class
            }
    )
    public ResponseEntity<ExceptionResponse> handleMemberNotFoundException(
            HttpServletRequest request,
            Exception e
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ExceptionResponse.of(
                        400,
                        e.getMessage(),
                        request.getRequestURI()
                )
        );
    }

    @ExceptionHandler(
            {
                    CustomAuthException.class
            }
    )
    public ResponseEntity<ExceptionResponse> handleCustomAuthException(
            HttpServletRequest request,
            CustomAuthException e
    ) {
        CustomAuthExceptionType exceptionType = e.getExceptionType();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                ExceptionResponse.of(
                        exceptionType.getCode(),
                        exceptionType.getMessage(),
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
