package com.java.springboot.microservice.service;

import com.java.springboot.microservice.EmployeeRepository;
import com.java.springboot.microservice.exception.ResourceNotFoundException;
import com.java.springboot.microservice.model.Employee;
import com.java.springboot.microservice.service.impl.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    public void setup(){
        //employeeRepository = Mockito.mock(EmployeeRepository.class);
        //employeeService = new EmployeeServiceImpl(employeeRepository);
        employee = Employee.builder()
                .id(1L)
                .firstName("Sriram")
                .lastName("Nagireddy")
                .email("sriram@gmail.com")
                .gender("Male")
                .build();
    }
   // JUnit test for saveEmployee method
    @Test
    @DisplayName("JUnit test for saveEmployee method")
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject() {
        // given - precondition or setup
        when(employeeRepository.findByEmail(employee.getEmail()))
                .thenReturn(Optional.empty());
        when(employeeRepository.save(employee)).thenReturn(employee);
        log.info("Employee Repository: " + employeeRepository);
        log.info("Employee Service: " + employeeService);
        // when -  action or the behaviour that we are going test
        Employee savedEmployee = employeeService.saveEmployee(employee);
        log.info("Employee saved: " + savedEmployee);
        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getEmail()).isEqualTo(employee.getEmail());
        assertThat(savedEmployee.getFirstName()).isSameAs(employee.getFirstName());
        verify(employeeRepository, times(1)).save(employee);
    }

        @Test
        @DisplayName("JUnit test for updateEmployee method")
        public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){
            // given
            Employee employee = new Employee();
                 employee.setId(1L);
                 employee.setFirstName("Sriram");
                 employee.setLastName("Nagireddy");
                 employee.setEmail("sriram@gmail.com");
                 employee.setGender("Male");

                 employee.setFirstName("Ram");
                 employee.setEmail("ram@gmail.com");
            // given - precondition or setup
            when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
            // when -  action or the behaviour that we are going test
             Employee updateEmployee=employeeService.updateEmployee(employee.getId(),employee);
            // then - verify the output
            verify(employeeRepository, times(1)).save(employee);
            verify(employeeRepository).findById(employee.getId());
            //assertThat(updateEmployee.getFirstName()).isEqualTo("Ram");
        }
    /*@Test
    @DisplayName("JUnit test for updateEmployee when employee does not exist method")
    public void givenEmployeeObject_whenUpdateEmployee_thenEmployeeDoesNotExist(){
        // given
        Employee employee = new Employee();
        employee.setId(10L);
        employee.setFirstName("Sriram");
        employee.setLastName("Nagireddy");
        employee.setEmail("sriram@gmail.com");
        employee.setGender("Male");

        employee.setFirstName("Ram");
        employee.setEmail("ram@gmail.com");
        // given - precondition or setup
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.ofNullable(employee));
        // when -  action or the behaviour that we are going test
      //employeeService.updateEmployee(employee.getId(),employee);
        // then - verify the output
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.updateEmployee(10L,employee);
        });
    }*/

    @Test
   // @DisplayName("JUnit test for getEmployeeById method")
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject(){
        // given
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.ofNullable(employee));
        // when
        Employee savedEmployee = employeeService.getEmployeeById(employee.getId());
        // then
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getFirstName()).isEqualTo(employee.getFirstName());
        assertThat(savedEmployee.getLastName()).isEqualTo(employee.getLastName());
        assertThat(savedEmployee.getEmail()).isEqualTo(employee.getEmail());
        assertThat(savedEmployee.getGender()).isEqualTo(employee.getGender());
    }


    @Test
    @DisplayName("JUnit test for findByEmail method")
    public void testAddEmployee_WhenEmployeeExists() {
        // Mocking an existing employee
        when(employeeRepository.findByEmail("sriram@gmail.com")).thenReturn(Optional.ofNullable(employee));
        // Attempting to add an employee that already exists
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.saveEmployee(employee);
        });

    }

    @Test
    @DisplayName("JUnit test for getAllEmployees method")
    public void givenEmployeesList_whenGetAllEmployees_thenReturnEmployeesList(){
        // given - precondition or setup

        Employee employee1 = Employee.builder()
                .firstName("siva")
                .lastName("kumar")
                .email("siva@gmail.com")
                .gender("Male")
                .build();
        when(employeeRepository.findAll()).thenReturn(List.of(employee,employee1));
        // when -  action or the behaviour that we are going test
        List<Employee> employeeList = employeeService.getAllEmployees();
        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }


    @Test
    @DisplayName("JUnit test for deleteEmployee method")
    public void givenEmployeeId_whenDeleteEmployee_ifFound(){
        // given - precondition or setup
        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        // when -  action or the behaviour that we are going test
        employeeService.deleteEmployee(employee.getId());
        // then - verify the output
        verify(employeeRepository, times(1)).deleteById(employee.getId());
    }


    @Test
    @DisplayName("JUnit test for deleteEmployeeifNotFound method")
    public void givenEmployeeId_whenDeleteEmployee_ifNotFound(){
        long exisingEmployeeId=10L;
        // given - precondition or setup
        when(employeeRepository.findById(exisingEmployeeId)).thenReturn(Optional.ofNullable(null));
        // when -  action or the behaviour that we are going test
        // then - verify the output
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.deleteEmployee(exisingEmployeeId);
        });
    }
}
