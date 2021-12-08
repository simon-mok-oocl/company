package com.restapitest.company.Repository;

import com.restapitest.company.Entity.Company;
import com.restapitest.company.Entity.Employee;
import com.restapitest.company.Exception.NoSuchCompanyException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CompanyRepository {
    List<Company> companies;

    public CompanyRepository()
    {
        this.companies = new ArrayList<>();

        Employee company1Employee1 = new Employee(1 , "c1e1" , 20 , "male" , 10);
        Employee company1Employee2 = new Employee(2 , "c1e2" , 30 , "female" , 11);

        Employee company2Employee1 = new Employee(1 , "c2e1" , 20 , "male" , 10);
        Employee company2Employee2 = new Employee(2 , "c2e2" , 40 , "female" , 15);

        Employee company3Employee1 = new Employee(1 , "c3e1" , 20 , "male" , 10);
        Employee company3Employee2 = new Employee(2 , "c3e2" , 40 , "female" , 15);

        companies.add(new Company(1 , "company 1" , Arrays.asList(company1Employee1 , company1Employee2)));
        companies.add(new Company(2 , "company 2" , Arrays.asList(company2Employee1 , company2Employee2)));
        companies.add(new Company(3 , "company 3" , Arrays.asList(company3Employee1 , company3Employee2)));
    }

    public List<Company> getCompanies() {
        return this.companies;
    }

    public Company addCompany(Company newCompany) {
        Integer nextId = this.companies.stream()
                .mapToInt(company -> company.getId())
                .max()
                .orElse(0) + 1;

        newCompany.setId(nextId);
        this.companies.add(newCompany);

        return newCompany;
    }

    public void removeCompany(Integer id) throws NoSuchCompanyException {
        Company toBeRemove = this.getCompanyById(id);

        this.companies.remove(toBeRemove);
    }

    public Company updateCompany(Integer id , Company companyPatch) throws NoSuchCompanyException {
        Company updateCompany = this.getCompanyById(id);

        updateCompany.setName(companyPatch.getName());
        return updateCompany;
    }

    public Company getCompanyById(Integer id) throws NoSuchCompanyException {
        return this.companies.stream()
                .filter(company -> company.getId().equals(id))
                .findFirst()
                .orElseThrow(NoSuchCompanyException::new);
    }

    public List<Employee> getEmployeeByCompany(Integer id) throws NoSuchCompanyException {
        Company useCompany = this.getCompanyById(id);

        return useCompany.getEmployee();
    }

    public List<Company> getCompanyByPage(Integer page, Integer pageSize) {
        return this.companies.stream()
                .skip((page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
}