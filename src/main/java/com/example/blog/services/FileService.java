package com.example.blog.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @project: Blog
 * @author: Sarvar55
 */
public interface FileService {

    String uploadImage(String path, MultipartFile file) throws IOException;

     InputStream getResurce(String path, String filename) throws FileNotFoundException;

}
