package com.restapitest.company;


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
        employees.add(new Employee(1 , "employee1" , 20 , "male" , 1000));
        employees.add(new Employee(2 , "employee2" , 21 , "female" , 2000));
        employees.add(new Employee(3 , "employee3" , 22 , "male" , 3000));
        employees.add(new Employee(4 , "employee4" , 23 , "female" , 4000));
        employees.add(new Employee(5 , "employee5" , 24 , "male" , 5000));
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

    public Employee editEmployeeAgeAndSalary(Integer id, Integer age, Integer salary) throws NoSuchEmployeeException {
        Employee patchEmployee = this.employees
                .stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchEmployeeException());

        patchEmployee.setAge(age);
        patchEmployee.setSalary(salary);

        return patchEmployee;
    }

    public void removeEmployee(Integer id) throws NoSuchEmployeeException {
        Employee toBeRemove = this.employees
                .stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchEmployeeException());

        this.employees.remove(toBeRemove);
    }
}
