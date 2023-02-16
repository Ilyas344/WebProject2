package com.example.webproject2.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public interface FileService {

    boolean saveFile(String json, String fileName);

    String readFile(String fileName);
    File getFromFile(String fileName);

    boolean clearFile(String fileName);

    Path saveRecipeTxt(String text, Path path) throws IOException;
}
