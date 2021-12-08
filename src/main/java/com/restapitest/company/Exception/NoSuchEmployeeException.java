package com.restapitest.company.Exception;

public class NoSuchEmployeeException extends RuntimeException {
    public NoSuchEmployeeException()
    {
        super("No such employee");
    }
}