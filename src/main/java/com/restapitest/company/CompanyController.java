package com.restapitest.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies")
public class CompanyController {
    private CompanyRepository companyRepository;
    private Object ResponseEntity;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    @GetMapping
    public List<Company> getCompanyList()
    {
        return companyRepository.getCompanies();
    }
    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Integer id) throws NoSuchCompanyException {
        return this.companyRepository.getCompanyById(id);
    }

    @PostMapping
    public ResponseEntity<Company> addCompany(@RequestBody Company company)
    {
        this.companyRepository.addCompany(company);
        return new ResponseEntity<Company>(company , HttpStatus.CREATED);
    }

    @PutMapping
    public Company updateCompany(@RequestBody Company company) throws NoSuchCompanyException {
        return this.companyRepository.updateCompany(company);
    }

    @DeleteMapping
    public String removeCompany(@RequestBody Company ripCompnay) throws NoSuchCompanyException {
        this.companyRepository.removeCompany(ripCompnay);
        return "";
    }
}
