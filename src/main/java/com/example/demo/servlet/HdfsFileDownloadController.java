package com.example.demo.servlet;


import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HdfsFileDownloadController {
    @Autowired
    private Configuration conf;

    @Value("${hdfs.path}")
    private String hdfsPath;

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String fileName) throws IOException {
        FileSystem fs = FileSystem.get(conf);
        Path filePath = new Path(hdfsPath + "/" + fileName);
        if (!fs.exists(filePath)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        InputStreamResource resource = new InputStreamResource(fs.open(filePath));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}


/**
 * **What does `{fileName:.+}` mean in API?**
 *
 * In an API, `{fileName:.+}` is a parameter that allows for the dynamic insertion of a file name into a URL or file path. The `.+` portion of the parameter is a regular expression that matches any character, one or more times, so that any file name can be used with the API.
 *
 * For example, in the Spring Boot implementation provided earlier, the path `/download/{fileName:.+}` indicates that the API expects a file name to be provided, which will be used to locate and download the requested file. When a user makes a request to this API endpoint with a specific file name, the API will use that name to locate the file on the server, and then return the contents of that file to the user in the response.
 *
 * This technique is often used in RESTful APIs to provide a dynamic way to access resources. By using parameters in the URL, an API can be designed to accept a wide variety of requests, all using a single endpoint.
 */