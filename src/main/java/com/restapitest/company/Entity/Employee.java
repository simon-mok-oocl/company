package com.restapitest.company.Entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("employee")
public class Employee {
    @MongoId(FieldType.OBJECT_ID)
    private String id = new String();
    private String name;
    private Integer age;
    private String gender;
    private Integer salary;
    private String companyId;

//    public Employee(Integer id, String name, Integer age, String gender, Integer salary) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//        this.gender = gender;
//        this.salary = salary;
//        this.companyId = -1;
//    }

    public Employee(Integer id, String name, Integer age, String gender, Integer salary , Integer companyId) {
        this.id = Integer.toString(id);
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.companyId = Integer.toString(companyId);
    }

    public Integer getCompanyId() {
        return Integer.valueOf(companyId);
    }

    public Integer getId() {
        return Integer.valueOf(id);
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
        this.id = Integer.toString(id);
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

        boolean sameId = Integer.valueOf(this.id) == ((Employee)obj).getId();
        boolean sameName = this.name.equals(((Employee)obj).getName());
        boolean sameAge = this.age == ((Employee)obj).getAge();
        boolean sameGender = this.gender == ((Employee)obj).getGender();
        boolean sameSalary = this.salary == ((Employee)obj).getSalary();

        return sameId && sameName && sameGender && sameSalary;
    }
}
