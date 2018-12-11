package com.chenhz.excel.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @PostMapping("/import")
    public String importExcel(@RequestParam("file")MultipartFile file){
        System.out.println(file.getOriginalFilename());
        List a = new ArrayList();
        a.add("1");
        List<String> ss= a.stream().map(d ->(d+"1")).collect(Collectors.toList())
    }
}
