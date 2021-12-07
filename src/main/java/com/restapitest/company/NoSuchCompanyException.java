package com.restapitest.company;

public class NoSuchCompanyException extends Exception {
    public NoSuchCompanyException()
    {
        super("No such company");
    }
}
