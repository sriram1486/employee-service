package com.java.springboot.microservice.repository;

import com.java.springboot.microservice.EmployeeRepository;
import com.java.springboot.microservice.model.Employee;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.assertj.core.api.Assertions;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    // JUnit test for saveEmployee
    @Test
    //@Order(1)
    @Rollback(value = false)
    public void saveEmployeeTest(){
        Employee employee = Employee.builder()
                .firstName("Sriram")
                .lastName("Nagireddy")
                .email("sriram@gmail.com")
                .gender("Male")
                .build();
        employeeRepository.save(employee);
        Assertions.assertThat(employee.getId()).isGreaterThan(0);
    }

}
