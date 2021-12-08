package com.restapitest.company.advice;

import com.restapitest.company.Exception.NoSuchCompanyException;
import com.restapitest.company.Exception.NoSuchEmployeeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
public class GlobalControllerAdvice {
    @ExceptionHandler({NoSuchEmployeeException.class})
    public ErrorResponse noEmployee(Exception exception)
    {
        return new ErrorResponse(404 , "Employee Not Found");
    }

    @ExceptionHandler({NoSuchCompanyException.class})
    public ErrorResponse noCompany(Exception exception)
    {
        return new ErrorResponse(404 , "Company Not Found");
    }
}
