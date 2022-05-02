package com.example.blog.services.impl;

import com.example.blog.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

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
        String name = file.getOriginalFilename();//abc.png
        //Fullpath


        //create folder if not created

        String randomID= UUID.randomUUID().toString();
        String encodeFileName=randomID.concat(name.substring(name.lastIndexOf(".")));

        File imageFile=new File(path);

        String filePath = path + File.separator + name;

        if(!imageFile.exists()){
            imageFile.mkdir();
        }
        //file copy
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return encodeFileName;
    }

    @Override
    public InputStream getResurce(String path, String filename) throws FileNotFoundException {
        String fullPath=path+File.separator+filename;
        InputStream inputStream=new FileInputStream(fullPath);
        return inputStream;
    }
}
