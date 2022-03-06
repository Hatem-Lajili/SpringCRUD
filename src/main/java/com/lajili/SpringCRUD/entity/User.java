package com.lajili.SpringCRUD.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "email", nullable = false, unique = true, length = 45)
    private String email;

    @Column(name = "password",nullable = false, length = 15)
    private String password;

    @Column(name = "firstName" ,length = 45, nullable = false)
    private String firstName;

    @Column(name = "lastName",length = 45, nullable = false)
    private String lastName;

    private boolean enabled;
}
