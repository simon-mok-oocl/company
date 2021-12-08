package com.restapitest.company;

public class NoSuchEmployeeException extends RuntimeException {
    public NoSuchEmployeeException()
    {
        super("No such employee");
    }
}