package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class FileController {
    @RequestMapping(value = "/images/{imageName}")
    @ResponseBody

    public byte[] getImage(@PathVariable(value = "imageName") String imageName) throws IOException {
        System.out.println(imageName);
        File file = new File("/Users/duongson/Desktop/Module4/TestJpa/src/main/webapp/WEB-INF/static/images/" + imageName);
        return Files.readAllBytes(file.toPath());
    }

}
