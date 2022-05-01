package com.example.blog.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @project: Blog
 * @author: Sarvar55
 */
public interface FileService {

    String uploadImage(String path, MultipartFile file) throws IOException;

}
