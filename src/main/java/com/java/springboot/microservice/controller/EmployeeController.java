package com.java.springboot.microservice.controller;

import com.java.springboot.microservice.model.Employee;
import com.java.springboot.microservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("employeeId") long employeeId) {
     return ResponseEntity.ok().body(employeeService.getEmployeeById(employeeId));
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("employeeId") long employeeId, @RequestBody Employee employee){
        Employee updatedEmployee = new Employee(employeeId,employee.getFirstName(),employee.getLastName(),employee.getEmail(),employee.getGender());
        return ResponseEntity.ok().body(employeeService.updateEmployee(employeeId,updatedEmployee));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long employeeId){
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);

    }

}
