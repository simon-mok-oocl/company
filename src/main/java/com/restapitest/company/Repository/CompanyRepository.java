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



        companies.add(new Company(1 , "company 1"));
        companies.add(new Company(2 , "company 2" ));
        companies.add(new Company(3 , "company 3"));
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

    public Company removeCompany(Integer id) throws NoSuchCompanyException {
        Company toBeRemove = this.getCompanyById(id);

        this.companies.remove(toBeRemove);

        return toBeRemove;
    }


    public Company save(Integer id , Company company)
    {
        this.removeCompany(id);
        this.companies.add(company);
        return company;
    }

    public Company getCompanyById(Integer id) throws NoSuchCompanyException {
        return this.companies.stream()
                .filter(company -> company.getId().equals(id))
                .findFirst()
                .orElseThrow(NoSuchCompanyException::new);
    }

//    public List<Employee> getEmployeeByCompany(Integer id) throws NoSuchCompanyException {
//        Company useCompany = this.getCompanyById(id);
//
//        return useCompany.getEmployee();
//    }

    public List<Company> getCompanyByPage(Integer page, Integer pageSize) {
        return this.companies.stream()
                .skip((page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public void clearAll() {
        this.companies.clear();
    }
}
