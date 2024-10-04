package com.globits.da.rest;

import com.globits.da.domain.address.District;
import com.globits.da.domain.address.Province;
import com.globits.da.dto.request.DistrictRequest;
import com.globits.da.dto.request.ProvinceRequest;
import com.globits.da.dto.response.DistrictResponse;
import com.globits.da.dto.response.ProvinceResponse;
import com.globits.da.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/district")
public class RestDistrictController {
    @Autowired
    private DistrictService districtService;

    @GetMapping("/districts")
    public ResponseEntity<?> getAll(){
        List<DistrictResponse> list = districtService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/search/{code}")
    public ResponseEntity<?> search(@PathVariable String code){
        Optional<District> optional = districtService.search(code);
        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("District not found with code: " + code, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<String> delete(@PathVariable String code){
        boolean isDeleted = districtService.delete(code);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK)
                    .body("District with code " + code + " was deleted successfully.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("District with code " + code + " was not found.");
        }
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DistrictRequest request){
        if (request.getName() == null || request.getName().isEmpty()) {
            return new ResponseEntity<>("District name is required", HttpStatus.BAD_REQUEST);
        }
        if (request.getCode() == null || request.getCode().isEmpty()) {
            return new ResponseEntity<>("District code is required", HttpStatus.BAD_REQUEST);
        }
        DistrictResponse response = districtService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{code}")
    public ResponseEntity<?> update(@RequestBody DistrictRequest request, @PathVariable String code){
        try {
            DistrictResponse update = districtService.update(code, request);
            return new ResponseEntity<>(update, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/districts/{provinceId}")
    public ResponseEntity<List<DistrictResponse>> getDistrictsByProvinceId(@PathVariable Integer provinceId) {
        List<DistrictResponse> districts = districtService.findDistrictsByProvinceId(provinceId);
        return new ResponseEntity<>(districts, HttpStatus.OK);
    }

    // Thêm Huyện và danh sách Xã
    @PostMapping
    public ResponseEntity<DistrictResponse> createDistrictWithCommunes(@Valid @RequestBody DistrictRequest request) {
        DistrictResponse response = districtService.createDistrictWithCommunes(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Sửa Huyện và danh sách Xã
    @PutMapping("/{code}")
    public ResponseEntity<DistrictResponse> updateDistrictWithCommunes(@PathVariable String code, @Valid @RequestBody DistrictRequest request) {
        DistrictResponse response = districtService.updateDistrictWithCommunes(code, request);
        return ResponseEntity.ok(response);
    }
}
