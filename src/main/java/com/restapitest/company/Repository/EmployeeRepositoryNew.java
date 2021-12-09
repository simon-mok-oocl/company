package com.restapitest.company.Repository;

import com.restapitest.company.Entity.Employee;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepositoryNew extends MongoRepository<Employee, String> {
}
