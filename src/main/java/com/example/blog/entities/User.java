package com.example.blog.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @project: Blog
 * @author: Sarvar55
 */
@Entity(name = "User")
@Table(name = "users")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "user_name", nullable = false, length = 100)
    private String name;
    @Column(name = "email", nullable = false)
    private String email;

    private String password;

    private String about;

}
