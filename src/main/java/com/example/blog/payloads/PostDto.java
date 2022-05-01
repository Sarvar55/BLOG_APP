package com.example.blog.payloads;

import com.example.blog.entities.Category;
import com.example.blog.entities.User;
import lombok.*;

import java.util.Date;

/**
 * @project: Blog
 * @author: Sarvar55
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDto {

    private Integer postId;

    private String title;

    private String content;

    private String imageName;

    private Date addedDate;

    private CategoryDto category;

    private UserDto user;

}
