package com.example.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @project: Blog
 * @author: Sarvar55
 */
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Integer categoryId;
    @NotBlank
    @Size(min = 10,message = "min size of category title is 10")
    private String categoryTitle;
    @NotBlank
    @Size(min = 10,message ="min size of category desc is 10" )
    private String categoryDescription;
}
