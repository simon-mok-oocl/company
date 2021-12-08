package com.restapitest.company.Repository;


import com.restapitest.company.Entity.Employee;
import com.restapitest.company.Exception.NoSuchEmployeeException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    List<Employee> employees;

    public EmployeeRepository()
    {
        employees = new ArrayList<>();
//        employees.add(new Employee(1 , "employee1" , 20 , "male" , 1000 , 1));
//        employees.add(new Employee(2 , "employee2" , 21 , "female" , 2000 , 1));
//        employees.add(new Employee(3 , "employee3" , 22 , "male" , 3000 ,1));
//        employees.add(new Employee(4 , "employee4" , 23 , "female" , 4000 , 1));
//        employees.add(new Employee(5 , "employee5" , 24 , "male" , 5000 ,1 ));

        Employee company1Employee1 = new Employee(1 , "c1e1" , 20 , "male" , 10 , 1);
        Employee company1Employee2 = new Employee(2 , "c1e2" , 30 , "female" , 11 , 1);

        Employee company2Employee1 = new Employee(1 , "c2e1" , 20 , "male" , 10 , 2);
        Employee company2Employee2 = new Employee(2 , "c2e2" , 40 , "female" , 15 , 2);

        Employee company3Employee1 = new Employee(1 , "c3e1" , 20 , "male" , 10 , 2);
        Employee company3Employee2 = new Employee(2 , "c3e2" , 40 , "female" , 15 , 2);

        employees.add(company1Employee1);
        employees.add(company1Employee2);
        employees.add(company2Employee1);
        employees.add(company2Employee2);
        employees.add(company3Employee1);
        employees.add(company3Employee2);
    }

    public List<Employee> getEmployeeList()
    {
        return this.employees;
    }

    public Employee getEmployeeById(Integer id) throws NoSuchEmployeeException {
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchEmployeeException());
    }

    public List<Employee> getEmployeeByGender(String gender) {
        return employees.stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeeInPage(Integer page, Integer pageSize) {
        return employees.stream()
                .skip((page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public Employee addEmployee(Employee newEmployee) {
        Integer nextId = this.employees.stream()
                .mapToInt(employee -> employee.getId())
                .max()
                .orElse(0) + 1;

        newEmployee.setId(nextId);
        this.employees.add(newEmployee);
        return newEmployee;
    }

//    public Employee updateEmployee(Integer id , Employee employee)
//    {
//        Employee useEmployee = this.getEmployeeById(id);
//
//        if(employee.getSalary() != null)
//            useEmployee.setSalary(employee.getSalary());
//        if(employee.getAge() != null)
//            useEmployee.setAge(employee.getAge());
//
//        return this.save(id , useEmployee);
//    }

    public Employee save(Integer id , Employee employee)
    {
        this.removeEmployee(id);
        this.employees.add(employee);
        return employee;
    }

    public Employee removeEmployee(Integer id) throws NoSuchEmployeeException {
        Employee toBeRemove = this.getEmployeeById(id);

        this.employees.remove(toBeRemove);

        return toBeRemove;
    }

    public void clearAll() {
        this.employees.clear();
    }
}
