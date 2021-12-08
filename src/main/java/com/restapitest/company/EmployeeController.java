package com.restapitest.company;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
@ControllerAdvice
public class EmployeeController {
    private EmployeeRepository emplyeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository)
    {
        this.emplyeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Employee> getEmployeeList()
    {
        return this.emplyeeRepository.getEmployeeList();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) throws NoSuchEmployeeException {
        return this.emplyeeRepository.getEmployeeById(id);
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getEmployeeByGender(@RequestParam String gender)
    {
        return this.emplyeeRepository.getEmployeeByGender(gender);
    }

    @GetMapping(params = {"page" , "pageSize"})
    public List<Employee> getEmployeeInPage(@RequestParam Integer page , @RequestParam Integer pageSize)
    {
        return this.emplyeeRepository.getEmployeeInPage(page , pageSize);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee newEmployee)
    {
        this.emplyeeRepository.addEmployee(newEmployee);
        return new ResponseEntity<Employee>(newEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Employee editEmployeeAgeAndSalary(@PathVariable Integer id , @RequestBody Employee employeePatch) throws NoSuchEmployeeException {
        Integer age = employeePatch.getAge();
        Integer salary = employeePatch.getSalary();
        return this.emplyeeRepository.editEmployeeAgeAndSalary(id , age , salary);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Integer id) throws NoSuchEmployeeException {
        this.emplyeeRepository.removeEmployee(id);
        return new ResponseEntity<Employee>( (Employee) null , HttpStatus.NO_CONTENT);
    }

}