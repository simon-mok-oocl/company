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

        this.companyRepository.save(id , useCompany);
        return this.getCompanyById(id);
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

    public Company getCompanyById(Integer id) {
        Company company = this.companyRepository.getCompanyById(id);

        List<Employee> employees = this.employeeRepository.getEmployeeByCompany(id);
        company.setEmployee(employees);

        return company;
    }


}
