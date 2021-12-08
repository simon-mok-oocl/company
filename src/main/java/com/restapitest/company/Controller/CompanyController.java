package com.restapitest.company.Controller;

import com.restapitest.company.Entity.Company;
import com.restapitest.company.Repository.CompanyRepository;
import com.restapitest.company.Entity.Employee;
import com.restapitest.company.Exception.NoSuchCompanyException;
import com.restapitest.company.Service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getCompanyList()
    {
        return this.companyService.getCompanies();
    }

    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable Integer id , @RequestBody Company company) throws NoSuchCompanyException {
        return this.companyService.updateCompany(id , company);
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Integer id) throws NoSuchCompanyException {
        return this.companyService.getCompanyById(id);
    }

    @PostMapping
    public ResponseEntity<Company> addCompany(@RequestBody Company company)
    {
        this.companyService.addCompany(company);
        return new ResponseEntity<Company>(company , HttpStatus.CREATED);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeeByCompany(@PathVariable Integer id) {
        return this.companyService.getEmployeeByCompany(id);
    }


    @GetMapping(params = {"page" , "pageSize"})
    public List<Company> getCompanyByPage(@RequestParam Integer page , @RequestParam Integer pageSize)
    {
        return this.companyService.getCompanyByPage(page , pageSize);
    }


//////////////////////////////////////////////////////////////////////////////////////////
//



//    @DeleteMapping("/{id}")
//    public ResponseEntity<Company> removeCompany(@PathVariable Integer id) throws NoSuchCompanyException {
//        this.companyRepository.removeCompany(id);
//        return new ResponseEntity<Company>((Company) null, HttpStatus.NO_CONTENT);
//        //return "";
//    }
}
