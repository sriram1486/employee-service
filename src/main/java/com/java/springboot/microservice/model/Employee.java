package com.java.springboot.microservice.model;

import lombok.*;

import jakarta.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column( name="email",nullable = false)
    private String email;

    @Column( name="gender",nullable = false)
    private String gender;
}
