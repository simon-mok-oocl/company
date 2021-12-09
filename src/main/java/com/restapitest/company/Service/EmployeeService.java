package com.restapitest.company.Service;

import com.restapitest.company.Entity.Employee;
import com.restapitest.company.Repository.EmployeeRepository;
import com.restapitest.company.Repository.EmployeeRepositoryNew;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;
    EmployeeRepositoryNew employeeRepositoryNew;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeRepositoryNew employeeRepositoryNew) {
        this.employeeRepository = employeeRepository;
        this.employeeRepositoryNew = employeeRepositoryNew;
    }

    public List<Employee> getEmployeeList() {
        return employeeRepositoryNew.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        //return null;
        return this.employeeRepository.getEmployeeById(id);
    }

    public Employee updateEmployee(Integer id, Employee employeePatch) {
        Employee employee = this.employeeRepository.getEmployeeById(id);

        if(employeePatch.getAge() != null)
            employee.setAge(employeePatch.getAge());
        if(employeePatch.getSalary() != null)
            employee.setSalary(employeePatch.getSalary());

        return this.employeeRepository.save(id , employee);
    }

    public List<Employee> getEmployeeByGender(String gender) {
        return this.employeeRepository.getEmployeeByGender(gender);
    }

    public Employee removeEmployee(Integer id) {
        return this.employeeRepository.removeEmployee(id);
    }

    public Employee addEmployee(Employee newEmployee) {
        return this.employeeRepository.addEmployee(newEmployee);
    }

    public List<Employee> getEmployeeInPage(Integer page, Integer pageSize) {
        return this.employeeRepository.getEmployeeInPage(page , pageSize);
    }
}
