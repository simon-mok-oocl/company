package com.restapitest.company.Entity;

import java.util.List;

public class Company {
    private String id;
    private String name;
    private List<Employee> employee;

    public Company(Integer id, String name) {
        this.id = Integer.toString(id);
        this.name = name;
        this.employee = null;
    }

    public Integer getId() {
        return Integer.valueOf(id);
    }

    public String getName() {
        return name;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setId(Integer id) {
        this.id = Integer.toString(id);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object obj)
    {
        boolean sameId = Integer.valueOf(this.id) == ((Company)obj).getId();
        boolean sameName = this.name == ((Company)obj).getName();

        return sameId && sameName;
    }
}
