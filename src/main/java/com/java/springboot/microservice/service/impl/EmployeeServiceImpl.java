package com.java.springboot.microservice.service.impl;

import com.java.springboot.microservice.model.Employee;
import com.java.springboot.microservice.EmployeeRepository;
import com.java.springboot.microservice.service.EmployeeService;
import com.java.springboot.microservice.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {

        Optional<Employee> savedEmployee = employeeRepository.findByEmail(employee.getEmail());
        if(savedEmployee.isPresent()){
            throw new ResourceNotFoundException("Employee already exist with given email:" + employee.getEmail());
        }
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with given id:" + id));
    }


    public Employee updateEmployee( Long employeeId,Employee employee) {
         employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with given id:" + employeeId));
            employee.setId(employeeId);
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(long employeeId) {
        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with given id:" + employeeId));
        employeeRepository.deleteById(employeeId);
    }
}
