package com.restapitest.company;

import com.restapitest.company.Entity.Employee;
import com.restapitest.company.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployeeList() {
        return employeeRepository.getEmployeeList();
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
}
