package com.sms.demo.util;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadUtil {


    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";


    // Method to save the uploaded file
    public static String saveFile(MultipartFile file) {
        String filename = file.getOriginalFilename();
        try {
            // Create the uploads directory if it doesn't exist
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Save the file to the specified directory
            Path filePath = uploadPath.resolve(filename);
            file.transferTo(filePath);

            return filename; // Return the file name to save it in the database
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
