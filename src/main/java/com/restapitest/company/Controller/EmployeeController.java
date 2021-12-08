package com.restapitest.company.Controller;


import com.restapitest.company.EmployeeService;
import com.restapitest.company.Entity.Employee;
import com.restapitest.company.Exception.NoSuchEmployeeException;
import com.restapitest.company.Repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
@ControllerAdvice
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployeeList() {
        return this.employeeService.getEmployeeList();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) throws NoSuchEmployeeException {
        return this.employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public Employee editEmployeeAgeAndSalary(@PathVariable Integer id , @RequestBody Employee employeePatch) throws NoSuchEmployeeException {
        return this.employeeService.updateEmployee(id , employeePatch);
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getEmployeeByGender(@RequestParam String gender)
    {
        return this.employeeService.getEmployeeByGender(gender);
    }

///////////////////////////////////////////////////////////////////////////////
//    @GetMapping("/{id}")
//    public Employee getEmployeeById(@PathVariable Integer id) throws NoSuchEmployeeException {
//        return this.emplyeeRepository.getEmployeeById(id);
//    }

//    @GetMapping(params = {"gender"})
//    public List<Employee> getEmployeeByGender(@RequestParam String gender)
//    {
//        return this.emplyeeRepository.getEmployeeByGender(gender);
//    }
//
//    @GetMapping(params = {"page" , "pageSize"})
//    public List<Employee> getEmployeeInPage(@RequestParam Integer page , @RequestParam Integer pageSize)
//    {
//        return this.emplyeeRepository.getEmployeeInPage(page , pageSize);
//    }
//
//    @PostMapping
//    public ResponseEntity<Employee> createEmployee(@RequestBody Employee newEmployee)
//    {
//        this.emplyeeRepository.addEmployee(newEmployee);
//        return new ResponseEntity<Employee>(newEmployee, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    public Employee editEmployeeAgeAndSalary(@PathVariable Integer id , @RequestBody Employee employeePatch) throws NoSuchEmployeeException {
//        return this.emplyeeRepository.updateEmployee(id , employeePatch);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Employee> deleteEmployee(@PathVariable Integer id) throws NoSuchEmployeeException {
//        this.emplyeeRepository.removeEmployee(id);
//        return new ResponseEntity<Employee>( (Employee) null , HttpStatus.NO_CONTENT);
//    }

}