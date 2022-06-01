package com.example.blog.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @project: Blog
 * @author: Sarvar55
 */
@Entity
@Table(name = "role")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    private String name;

}
