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

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeeByCompany(@PathVariable Integer id) throws NoSuchCompanyException {
        return this.companyRepository.getEmployeeByCompany(id);
    }

    @GetMapping(params = {"page" , "pageSize"})
    public List<Company> getCompanyByPage(@RequestParam Integer page , @RequestParam Integer pageSize)
    {
        return this.companyRepository.getCompanyByPage(page , pageSize);
    }

    @PostMapping
    public ResponseEntity<Company> addCompany(@RequestBody Company company)
    {
        this.companyRepository.addCompany(company);
        return new ResponseEntity<Company>(company , HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable Integer id , @RequestBody Company company) throws NoSuchCompanyException {
        return this.companyRepository.updateCompany(id , company);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Company> removeCompany(@PathVariable Integer id) throws NoSuchCompanyException {
        this.companyRepository.removeCompany(id);
        return new ResponseEntity<Company>((Company) null, HttpStatus.NO_CONTENT);
        //return "";
    }
}
