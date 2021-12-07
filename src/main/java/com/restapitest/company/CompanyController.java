package com.restapitest.company;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies")
public class CompanyController {
    private CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping
    public List<Company> getCompanyList()
    {
        return companyRepository.getCompanies();
    }

    @PostMapping
    public Company addCompany(@RequestBody Company company)
    {
        return this.companyRepository.addCompany(company);
    }
}
