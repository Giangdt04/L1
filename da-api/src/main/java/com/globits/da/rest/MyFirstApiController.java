package com.globits.da.rest;

import com.globits.da.dto.MyFirstApiDto;
import com.globits.da.service.impl.MyFirstApiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MyFirstApiController {
    @Autowired
    MyFirstApiServiceImpl myFirstApiService;

    @GetMapping("/MyFirstApi")
    public String myFirstAPI() {
        return "MyFirstApi";
    }

    @GetMapping("/MyFirstApiService")
    public String myFirstApiService() {
        return myFirstApiService.myFirstApiService();
    }

    @PostMapping("/MyFirstApi/{code}")
    public MyFirstApiDto createMyFirstApi(
            @PathVariable String code,
            @RequestBody MyFirstApiDto myFirstApiDTO) {
        myFirstApiDTO.setCode(code); // Sử dụng giá trị từ PathVariable
        return myFirstApiDTO;
    }

    @PostMapping("/UploadFile")
    public String uploadFile(
            @RequestParam("file") MultipartFile file) {
        try {
            // Đọc file và in ra nội dung từng dòng
            String content = new BufferedReader(new InputStreamReader(file.getInputStream()))
                    .lines()
                    .collect(Collectors.joining("\n"));
            System.out.println("File Content:");
            System.out.println(content);
            return "File uploaded and content printed successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to upload file!";
        }
    }

    //    @PostMapping("/MyFirstApi")
//    public MyFirstApiDto createMyFirstApi(
//            @ModelAttribute MyFirstApiDto myFirstApiDto) {
//        return myFirstApiDto;
//    }

//    @PostMapping("/MyFirstApi")
//    public MyFirstApiDto createMyFirstApi(MyFirstApiDto myFirstApiDto) {
//        return myFirstApiDto;
//    }
}
