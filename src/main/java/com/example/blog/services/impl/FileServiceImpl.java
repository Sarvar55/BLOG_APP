package com.example.blog.services.impl;

import com.example.blog.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @project: Blog
 * @author: Sarvar55
 */
@Service
public class FileServiceImpl implements FileService {
//    File.separator = /
//    File.separatorChar = /
//    File.pathSeparator = :
//    File.pathSeparatorChar = :

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        //File name
        String name = file.getOriginalFilename();
        //Fullpath
        String filePath = path + File.separator + name;

        //create folder if not created
        File imageFile=new File(path);
        if(!imageFile.exists()){
            imageFile.mkdir();
        }
        //file copy
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return name;
    }
}
