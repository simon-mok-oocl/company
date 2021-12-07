package com.restapitest.company;

public class NoSuchEmployeeException extends Exception {
    public NoSuchEmployeeException()
    {
        super("No such employee");
    }
}