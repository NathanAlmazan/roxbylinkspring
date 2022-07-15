package com.information.roxbylink.errors;

import com.information.roxbylink.errors.types.EntityNotFoundException;
import com.information.roxbylink.errors.types.ForbiddenException;
import com.information.roxbylink.errors.types.InternalErrorException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomErrorAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Object> handleInternalErrorException(
            ForbiddenException ex, WebRequest request
    ) {
        CustomErrorResponse error = new CustomErrorResponse(403);
        error.addErrorMessage(ex.getMessage());
        return new ResponseEntity<>(error.getErrorObject(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<Object> handleInternalErrorException(
            InternalErrorException ex, WebRequest request
    ) {
        CustomErrorResponse error = new CustomErrorResponse(500);
        error.addErrorMessage(ex.getMessage());
        return new ResponseEntity<>(error.getErrorObject(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(
            EntityNotFoundException ex, WebRequest request
    ) {
        CustomErrorResponse error = new CustomErrorResponse(404);
        error.addErrorMessage(ex.getMessage());
        return new ResponseEntity<>(error.getErrorObject(), HttpStatus.NOT_FOUND);
    }


    @NonNull @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NonNull MethodArgumentNotValidException ex, @NonNull HttpHeaders headers, HttpStatus status, @NonNull WebRequest request
    ) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        CustomErrorResponse error = new CustomErrorResponse(status.value());
        error.setErrorMessage(errors);

        return new ResponseEntity<>(error.getErrorObject(), HttpStatus.BAD_REQUEST);
    }
}
