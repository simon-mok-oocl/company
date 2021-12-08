package com.restapitest.company.Entity;

import java.util.List;

public class Company {
    private Integer id;
    private String name;
    private List<Employee> employee;

    public Company(Integer id, String name, List<Employee> employee) {
        this.id = id;
        this.name = name;
        this.employee = employee;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }
}
