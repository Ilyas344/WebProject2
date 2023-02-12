package com.example.webproject2.service;

import java.io.IOException;

public interface FileService {

    boolean saveFile(String json, String fileName);

    String readFile(String fileName);

    boolean clearFile(String fileName);
}
