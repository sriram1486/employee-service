package com.java.springboot.microservice;

import com.java.springboot.microservice.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class EmployeeServiceApplication implements CommandLineRunner {

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Employee employee = Employee.builder()
				.firstName("Sriram")
				.lastName("Nagireddy")
				.email("sriram@gmail.com")
				.gender("Male")
				.build();
		employeeRepository.save(employee);
		Employee employee1 = Employee.builder()
				.firstName("siva")
				.lastName("kumar")
				.email("siva@gmail,com")
				.gender("Male")
				.build();
		employeeRepository.save(employee1);

		Employee employee2 = Employee.builder()
				.firstName("ravi")
				.lastName("kumar")
				.email("ravi@gmail,com")
				.gender("Male")
				.build();
		employeeRepository.save(employee2);

		Employee employee3 = Employee.builder()
				.firstName("sunitha")
				.lastName("kumari")
				.email("sunitha@gmail,com")
				.gender("Female")
				.build();
		employeeRepository.save(employee3);
		Employee employee4 = Employee.builder()
				.firstName("rama")
				.lastName("jyothi")
				.email("jyothi@gmail,com")
				.gender("Female")
				.build();
		employeeRepository.save(employee4);

	}
}
