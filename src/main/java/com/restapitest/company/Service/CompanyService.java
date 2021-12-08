package com.restapitest.company.Service;

import com.restapitest.company.Entity.Company;
import com.restapitest.company.Repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company updateCompany(Integer id, Company company) {
        Company useCompany = this.companyRepository.getCompanyById(id);

        if(company.getName() != null)
            useCompany.setName(company.getName());

        return this.companyRepository.save(id , useCompany);
    }

    public List<Company> getCompanies() {
        return this.companyRepository.getCompanies();
    }
}
