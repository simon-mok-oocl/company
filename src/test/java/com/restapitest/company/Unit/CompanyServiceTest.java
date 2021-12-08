package com.restapitest.company.Unit;

import com.restapitest.company.Entity.Company;
import com.restapitest.company.Entity.Employee;
import com.restapitest.company.Repository.CompanyRepository;
import com.restapitest.company.Repository.EmployeeRepository;
import com.restapitest.company.Service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class CompanyServiceTest {
    @Mock
    CompanyRepository companyRepository;

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    CompanyService companyService;

    @Test
    public void should_return_company_list_when_getCompanyList()
    {
        Company company = new Company(1, "spring");
        List<Company> companies = new ArrayList<>();
        companies.add(company);
        given(companyRepository.getCompanies()).willReturn(companies);

        List<Company> actual = companyService.getCompanies();

        assertEquals(companies , actual);

    }

    @Test
    void should_update_company_when_updateCompany_given_company()
    {
        Company company = new Company(1 , "company1");
        given(companyRepository.getCompanyById(any())).willReturn(company);

        Company companyPatch = new Company(1 , "new company1");
        company.setName("new company1");
        given(companyRepository.save(any() , any())).willReturn(company);

        Company patchedCompany = companyService.updateCompany(1 , companyPatch);

        assertEquals(companyPatch , patchedCompany);
    }

    @Test
    public void should_return_company_when_getCompanyById_given_company()
    {
        Company company = new Company(1, "spring");
        given(companyRepository.getCompanyById(any())).willReturn(company);

        Company actual = companyService.getCompanyById(1);

        assertEquals(company , actual);

    }

    @Test
    public void should_add_company_when_addCompany_given_company()
    {
        Company company = new Company(1, "spring");
        given(companyRepository.addCompany(any())).willReturn(company);

        Company actual = companyService.addCompany(company);

        assertEquals(company , actual);

    }

    @Test
    public void should_return_employee_when_getEmployeeByCompany_given_company_id()
    {
//        Employee employee = new Employee(1 , "employee1" , 11 , "male" , 100 , 1);
//        List<Employee> employees = new ArrayList<>();
//        employees.add(employee);
//        employeeRepository.addEmployee(employee);
//        given(companyRepository.getEmployeeByCompany(any())).willReturn(employees);
//
//        List<Employee> actual = companyService.getEmployeeByCompany(1);
//
//        assertEquals(employees , actual);

        Company company = new Company(1, "spring");
        Employee employee = new Employee(1 , "employee 1" , 1, "female" ,1 , 1);
        companyRepository.addCompany(company);
        employeeRepository.addEmployee(employee);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        given(employeeRepository.getEmployeeByCompany(any())).willReturn(employees);

        List<Employee> actual = employeeRepository.getEmployeeByCompany(1);

        assertEquals(employees , actual);
    }

    @Test
    public void should_return_company_page_when_getCompanyByPage_given_page_pageSize()
    {
        Company company = new Company(1, "spring");
        List<Company> companies = new ArrayList<>();
        companies.add(company);
        given(companyRepository.getCompanyByPage(any() , any())).willReturn(companies);

        List<Company> actual = companyService.getCompanyByPage(1,1);

        assertEquals(companies , actual);

    }

    @Test
    public void should_return_company_when_deleteCompany_given_company()
    {
        Company company = new Company(1, "spring");

        given(companyRepository.removeCompany(any())).willReturn(company);

        Company actual = companyService.removeCompany(1);

        assertEquals(company , actual);

    }
}
