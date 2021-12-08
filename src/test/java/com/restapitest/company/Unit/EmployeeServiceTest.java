package com.restapitest.company;

import com.restapitest.company.Entity.Employee;
import com.restapitest.company.Repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {
    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    com.restapitest.company.EmployeeService employeeService;

    @Test
    public void should_return_employee_list_when_getEmployeeList()
    {
        Employee employee = new Employee(1 , "employee 1" , 10 , "male" , 100);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        given(employeeRepository.getEmployeeList()).willReturn(employees);

        List<Employee> actual = employeeService.getEmployeeList();

        assertEquals(employees , actual);

    }

    @Test
    public void should_return_correct_employee_list_when_getEmployee_given_id()
    {
        Employee employee = new Employee(1 , "employee 1" , 10 , "male" , 100);
        given(employeeRepository.getEmployeeById(any())).willReturn(employee);

        Employee actual = employeeService.getEmployeeById(1);

        assertEquals(employee , actual);

    }

    @Test
    public void should_return_correct_gender_list_when_getEmployeeByGender_given_gender()
    {
        Employee employee = new Employee(1 , "employee 1" , 10 , "male" , 100);
        List<Employee> male = new ArrayList<>();
        male.add(employee);
        given(employeeRepository.getEmployeeByGender(any())).willReturn(male);

        List<Employee> actual = employeeService.getEmployeeByGender("male");

        assertEquals(male , actual);

    }

    @Test
    void should_return_edited_employee_when_editEmployee_given_employee_patch()
    {
        // given
        Employee employee = new Employee(1 , "employee 1" , 10 , "male" , 100);
        given(employeeRepository.getEmployeeById(any())).willReturn(employee);


        Employee employeePatch = new Employee(1 , "employee 1" , 11 , "male" , 200);
        employee.setAge(11);
        employee.setSalary(200);
        given(employeeRepository.save(any(),any())).willReturn(employee);

        // when
        Employee patchedEmployee = employeeService.updateEmployee(1 , employeePatch);

        // then
        assertEquals(employeePatch , patchedEmployee);
    }

}
