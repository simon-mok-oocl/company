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

    @PostMapping
    public ResponseEntity<Company> addCompany(@RequestBody Company company)
    {
        this.companyRepository.addCompany(company);
        return new ResponseEntity<Company>(company , HttpStatus.CREATED);
    }

    @DeleteMapping
    public String removeCompany(@RequestBody Company ripCompnay) throws NoSuchCompanyException {
        this.companyRepository.removeCompany(ripCompnay);
        return "";
    }
}
