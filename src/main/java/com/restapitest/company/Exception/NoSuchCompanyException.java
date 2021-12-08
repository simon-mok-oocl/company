package com.restapitest.company.Exception;

public class NoSuchCompanyException extends RuntimeException {
    public NoSuchCompanyException()
    {
        super("No such company");
    }
}
