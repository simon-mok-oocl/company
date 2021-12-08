package com.restapitest.company.Entity;

public class Employee {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private Integer salary;
    private Integer companyId;

//    public Employee(Integer id, String name, Integer age, String gender, Integer salary) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//        this.gender = gender;
//        this.salary = salary;
//        this.companyId = -1;
//    }

    public Employee(Integer id, String name, Integer age, String gender, Integer salary , Integer companyId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.companyId = companyId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object obj)
    {

        boolean sameId = this.id == ((Employee)obj).getId();
        boolean sameName = this.name.equals(((Employee)obj).getName());
        boolean sameAge = this.age == ((Employee)obj).getAge();
        boolean sameGender = this.gender == ((Employee)obj).getGender();
        boolean sameSalary = this.salary == ((Employee)obj).getSalary();

        return sameId && sameName && sameGender && sameSalary;
    }
}
