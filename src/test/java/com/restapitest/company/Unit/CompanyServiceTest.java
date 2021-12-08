package com.restapitest.company.Unit;

import com.restapitest.company.Entity.Company;
import com.restapitest.company.Entity.Employee;
import com.restapitest.company.Repository.CompanyRepository;
import com.restapitest.company.Service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class CompanyServiceTest {
    @Mock
    CompanyRepository companyRepository;

    @InjectMocks
    CompanyService companyService;

    @Test
    public void should_return_company_list_when_getCompanyList()
    {
//        Employee employee = new Employee(1 , "employee 1" , 10 , "male" , 100);
//        List<Employee> employees = new ArrayList<>();
//        employees.add(employee);
//        given(employeeRepository.getEmployeeList()).willReturn(employees);
//
//        List<Employee> actual = employeeService.getEmployeeList();
//
//        assertEquals(employees , actual);

        Company company = new Company(1, "spring");
        List<Company> companies = new ArrayList<>();
        companies.add(company);
        given(companyRepository.getCompanies()).willReturn(companies);

        List<Company> actual = companyService.getCompanies();

        assertEquals(companies , actual);

    }

}
