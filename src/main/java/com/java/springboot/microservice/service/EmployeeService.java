package com.java.springboot.microservice.service;

import com.java.springboot.microservice.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
   Employee getEmployeeById(long id);
    Employee updateEmployee(Long employeeId,Employee updatedEmployee);
    void deleteEmployee(long id);
}
