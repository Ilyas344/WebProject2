package com.example.webproject2.service.impl;


import com.example.webproject2.service.FileService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
@Data
public class FileServiceImpl implements FileService {
    @Value("${path.to.file}")
    private String filePath;


    @Override
    public boolean saveFile(String json, String fileName) {
        try {
            clearFile(fileName);
            Files.writeString(Path.of(filePath, fileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }


    @Override
    public String readFile(String fileName) {
        try {
            return Files.readString(Path.of(filePath, fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean clearFile(String fileName) {
        try {
            Files.deleteIfExists(Path.of(filePath, fileName));
            Files.createFile(Path.of(filePath, fileName));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
