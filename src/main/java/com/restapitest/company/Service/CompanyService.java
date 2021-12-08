package com.restapitest.company.Service;

import com.restapitest.company.Entity.Company;
import com.restapitest.company.Entity.Employee;
import com.restapitest.company.Repository.CompanyRepository;
import com.restapitest.company.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;
    private EmployeeRepository employeeRepository;

    public CompanyService(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    public Company updateCompany(Integer id, Company company) {
        Company useCompany = this.companyRepository.getCompanyById(id);

        if(company.getName() != null)
            useCompany.setName(company.getName());

        return this.companyRepository.save(id , useCompany);
    }

    public List<Company> getCompanies() {
        List<Company> companies = this.companyRepository.getCompanies();

        for(Company company : companies)
        {
            List<Employee> employees = this.employeeRepository.getEmployeeByCompany(company.getId());
            company.setEmployee(employees);
        }

        return this.companyRepository.getCompanies();
    }
}
