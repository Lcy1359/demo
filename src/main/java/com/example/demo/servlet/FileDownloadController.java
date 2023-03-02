package com.example.demo.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileDownloadController {
    private static final String FILE_DIRECTORY = "/path/to/files";

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) throws IOException {
        File file = new File(FILE_DIRECTORY + File.separator + fileName);
        if (!file.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        FileInputStream inputStream = new FileInputStream(file);
        byte[] contents = new byte[(int) file.length()];
        inputStream.read(contents);
        inputStream.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentLength(contents.length);

        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }
}
