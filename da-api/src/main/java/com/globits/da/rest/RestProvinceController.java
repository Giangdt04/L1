package com.globits.da.rest;

import com.globits.da.domain.address.Province;
import com.globits.da.dto.request.ProvinceRequest;
import com.globits.da.dto.response.ProvinceResponse;
import com.globits.da.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/province")
public class RestProvinceController {
    @Autowired
    ProvinceService provinceService;

    @GetMapping("/provinces")
    public ResponseEntity<?> getAll(){
        List<ProvinceResponse> list = provinceService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/search/{code}")
    public ResponseEntity<?> search(@PathVariable String code){
        Optional<Province> optional = provinceService.search(code);
        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Province not found with code: " + code, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteByCode/{code}")
    public ResponseEntity<String> delete(@PathVariable String code){
        boolean isDeleted = provinceService.delete(code);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Province with code " + code + " was deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Province with code " + code + " was not found.");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProvinceRequest request){
        if (request.getName() == null || request.getName().isEmpty()) {
            return new ResponseEntity<>("Province name is required", HttpStatus.BAD_REQUEST);
        }
        if (request.getCode() == null || request.getCode().isEmpty()) {
            return new ResponseEntity<>("Province code is required", HttpStatus.BAD_REQUEST);
        }
        ProvinceResponse response = provinceService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{code}")
    public ResponseEntity<?> update(@RequestBody ProvinceRequest request, @PathVariable String code){
        try {
            // Cập nhật tỉnh
            ProvinceResponse updatedProvince = provinceService.update(code, request);
            return new ResponseEntity<>(updatedProvince, HttpStatus.OK);
        } catch (RuntimeException e) {
            // Xử lý lỗi nếu tỉnh không tìm thấy
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    // Thêm Tỉnh và danh sách Huyện
    @PostMapping
    public ResponseEntity<ProvinceResponse> createProvinceWithDistricts(@Valid @RequestBody ProvinceRequest request) {
        ProvinceResponse response = provinceService.createProvinceWithDistricts(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Sửa Tỉnh và danh sách Huyện
    @PutMapping("/{code}")
    public ResponseEntity<ProvinceResponse> updateProvinceWithDistricts(@PathVariable String code, @Valid @RequestBody ProvinceRequest request) {
        ProvinceResponse response = provinceService.updateProvinceWithDistricts(code, request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/createAll")
    public ResponseEntity<ProvinceResponse> createProvinceWithDistrictsAndCommunes(@Valid @RequestBody ProvinceRequest request) {
        ProvinceResponse response = provinceService.createProvinceWithDistrictsAndCommunes(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
