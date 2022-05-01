package com.letscode.store.config;

import com.letscode.store.exception.AlreadyExistException;
import com.letscode.store.exception.ErrorValidation;
import com.letscode.store.exception.NotEnoughException;
import com.letscode.store.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class ErrorHandle {

    @Autowired
    private final MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorValidation> HandleErrorValidation(MethodArgumentNotValidException exception){
        List<ErrorValidation> errors = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e ->{
            String message =  messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErrorValidation error = new ErrorValidation(e.getField(), message);
            errors.add(error);
        });
        return  errors;
    }


    @ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(NotFoundException.class)
    public String notFoundHandler( NotFoundException exception){ return exception.getMessage();}

    @ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(AlreadyExistException.class)
    public String alreadyExistHandler( AlreadyExistException exception){ return exception.getMessage();}

    @ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(NotEnoughException.class)
    public String notEnoughHandler( NotEnoughException exception){ return exception.getMessage();}
}
