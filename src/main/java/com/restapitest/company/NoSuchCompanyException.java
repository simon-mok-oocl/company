package com.restapitest.company;

public class NoSuchCompanyException extends RuntimeException {
    public NoSuchCompanyException()
    {
        super("No such company");
    }
}
