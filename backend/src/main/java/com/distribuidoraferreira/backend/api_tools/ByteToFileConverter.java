package com.distribuidoraferreira.backend.api_tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class ByteToFileConverter {
    public static File convertMultipartFileToFile(MultipartFile file) throws IOException {

        File tempFile = File.createTempFile(file.getName(), ".png");
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(file.getBytes());
        }
        return tempFile;
    }
}
