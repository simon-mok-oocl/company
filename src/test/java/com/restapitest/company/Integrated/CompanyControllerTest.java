package com.restapitest.company.Integrated;

import com.restapitest.company.Entity.Company;
import com.restapitest.company.Entity.Employee;
import com.restapitest.company.Repository.CompanyRepository;
import com.restapitest.company.Repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        companyRepository.clearAll();
        employeeRepository.clearAll();
    }

    @Test
    public void should_get_all_companies_when_getAllCompanies_given_companies() throws Exception {
        // given
        Company company = new Company(1, "spring");
        Employee employee = new Employee(1 , "Lily1" , 20 , "female" , 8000 , 1);
        companyRepository.addCompany(company);
        employeeRepository.addEmployee(employee);

        // when

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/companies"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("spring"))
                .andExpect(jsonPath("$[0].employee", hasSize(1)))
                .andExpect(jsonPath("$[0].employee[0].name").value("Lily1"))
                .andExpect(jsonPath("$[0].employee[0].age").value(20))
                .andExpect(jsonPath("$[0].employee[0].gender").value("female"))
                .andExpect(jsonPath("$[0].employee[0].salary").value(8000));

    }

    @Test
    public void should_return_correct_companies_when_getAllCompanies_given_id() throws Exception {
        // given
        Company company = new Company(1, "spring");
        this.employeeRepository.addEmployee(new Employee(1, "Lily1", 20, "Female", 8000 , 1));
        companyRepository.addCompany(company);

        // when

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}" , company.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("spring"))
                .andExpect(jsonPath("$.employee", hasSize(1)))
                .andExpect(jsonPath("$.employee[0].name").value("Lily1"))
                .andExpect(jsonPath("$.employee[0].age").value(20))
                .andExpect(jsonPath("$.employee[0].gender").value("Female"))
                .andExpect(jsonPath("$.employee[0].salary").value(8000));

    }
//
//    @Test
//    public void should_get_all_employees_under_company_when_getAllEmployeesByCompanyId_given_id() throws Exception {
//        // given
//        Company company = new Company(1, "spring");
//        company.setEmployees(Collections.singletonList(new Employee(1, "Lily1", 20, "Female", 8000)));
//        companyRepository.create(company);
//
//        // when
//
//        // then
//        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}/employees", company.getId()))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(jsonPath("$",hasSize(1)))
//                .andExpect(jsonPath("$[0].id").isNumber())
//                .andExpect(jsonPath("$[0].name").value("Lily1"))
//                .andExpect(jsonPath("$[0].age").value(20))
//                .andExpect(jsonPath("$[0].gender").value("Female"))
//                .andExpect(jsonPath("$[0].salary").value(8000));
//
//    }
//
//
//    @Test
//    public void should_company_in_page_when_getAllEmployeesByCompanyId_given_id() throws Exception {
//        // given
//        Company company = new Company(1, "spring");
//        company.setEmployees(Collections.singletonList(new Employee(1, "Lily1", 20, "Female", 8000)));
//        companyRepository.create(company);
//
//        // when
//
//        // then
//        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}/employees", company.getId()))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(jsonPath("$",hasSize(1)))
//                .andExpect(jsonPath("$[0].id").isNumber())
//                .andExpect(jsonPath("$[0].name").value("Lily1"))
//                .andExpect(jsonPath("$[0].age").value(20))
//                .andExpect(jsonPath("$[0].gender").value("Female"))
//                .andExpect(jsonPath("$[0].salary").value(8000));
//
//    }
//
//    @Test
//    public void should_comapny_in_page_under_company_when_getAllCompanyByPage_given_page_pageSize() throws Exception {
//        // given
//        Company company1 = new Company(1, "spring");
//        company1.setEmployees(Collections.singletonList(new Employee(1, "Lily1", 20, "Female", 8000)));
//        companyRepository.create(company1);
//
//        Company company2 = new Company(2, "spring2");
//        Company company3 = new Company(3, "spring3");
//
//        companyRepository.create(company2);
//        companyRepository.create(company3);
//
//        // when
//
//        // then
//        mockMvc.perform((MockMvcRequestBuilders.get("/companies").param("page", "1")).param("pageSize" , "2"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(jsonPath("$",hasSize(1)))
//                .andExpect(jsonPath("$[0].id").value(3))
//                .andExpect(jsonPath("$[0].employees" , hasSize(0)));
//
//    }
//
    @Test
    public void should_create_company_when_createCompany_given_company() throws Exception {
        // given
        String company = "{\n" +
                "        \"name\": \"spring\"" +
                "    }";

        // when

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(company))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("spring"));

    }


    @Test
    public void should_update_comapny_when_updateCompany_given_company() throws Exception {
        // given
        Company company1 = new Company(1, "spring");
        this.employeeRepository.addEmployee(new Employee(1, "Lily1", 20, "Female", 8000 , 1));
        companyRepository.addCompany(company1);

        String newCompany = "{\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"new spring\",\n" +
                "        \"employee\": [\n" +
                "            {\n" +
                "                \"id\": 1,\n" +
                "                \"name\": \"Lily1\",\n" +
                "                \"age\": 20,\n" +
                "                \"gender\": \"Female\",\n" +
                "                \"salary\": 8000\n" +
                "            }\n" +
                "        ]\n" +
                "    }";

        // when

        // then
        mockMvc.perform((MockMvcRequestBuilders.put("/companies/{id}" , company1.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newCompany))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("new spring"));

    }
//
//    @Test
//    public void should_delete_company_with_id_when_deleteCompanyById_given_id() throws Exception {
//        // given
//        Company company = new Company(1, "spring");
//        company.setEmployees(Collections.singletonList(new Employee(1, "Lily1", 20, "Female", 8000)));
//        companyRepository.create(company);
//
//        // when
//
//        // then
//        mockMvc.perform((MockMvcRequestBuilders.delete("/companies/{id}", company.getId())))
//                .andExpect(MockMvcResultMatchers.status().isNoContent());
//
//    }

}