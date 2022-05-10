package com.example.blog.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @project: Blog
 * @author: Sarvar55
 */
@Entity
@Table(name = "comments")
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    private String content;

    @ManyToOne
    private Post post;

}
