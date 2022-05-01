package com.example.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @project: Blog
 * @author: Sarvar55
 */
@Getter
@Setter
@NoArgsConstructor
public class PostResponse<E> {
    private List<E> content;
    private long totalElements;
    private int pageSize;
    private int pageNumber;
    private boolean isLast;
    private int totalPages;
}
