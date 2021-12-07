package com.restapitest.company;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        companies.add(new Company(1 , "company 1" , Arrays.asList(company1Employee1 , company1Employee2)));
        companies.add(new Company(2 , "company 2" , Arrays.asList(company1Employee1 , company2Employee2)));
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

    public void removeCompany(Company ripCompnay) throws NoSuchCompanyException {
        Company toBeRemove = this.companies
                .stream()
                .filter(company -> company.getId() == ripCompnay.getId())
                .findFirst()
                .orElseThrow(() -> new NoSuchCompanyException());

        this.companies.remove(toBeRemove);
    }

    public Company updateCompany(Company companyPatch) throws NoSuchCompanyException {
        Company updateCompany = this.companies
                .stream()
                .filter(company -> company.getId() == companyPatch.getId())
                .findFirst()
                .orElseThrow(() -> new NoSuchCompanyException());

        updateCompany.setName(companyPatch.getName());
        return updateCompany;
    }

    public Company getCompanyById(Integer id) throws NoSuchCompanyException {
        return this.companies.stream()
                .filter(company -> company.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchCompanyException());
    }
}
