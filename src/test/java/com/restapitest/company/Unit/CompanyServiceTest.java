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
import java.util.Collections;
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

}
