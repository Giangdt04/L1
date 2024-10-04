package com.globits.da.rest;

import com.globits.da.domain.Certificate;
import com.globits.da.domain.address.Commune;
import com.globits.da.dto.request.CertificateRequest;
import com.globits.da.dto.request.CommuneRequest;
import com.globits.da.dto.response.CertificateResponse;
import com.globits.da.dto.response.CommuneResponse;
import com.globits.da.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/certificate")
public class RestCertificateController {
    @Autowired
    private CertificateService certificateService;

    @GetMapping("/certificates")
    public ResponseEntity<?> getAll(){
        List<CertificateResponse> list = certificateService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/search/{code}")
    public ResponseEntity<?> search(@PathVariable String code){
        Optional<Certificate> optional = certificateService.search(code);
        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Certificate not found with code: " + code, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<String> delete(@PathVariable String code){
        boolean isDeleted = certificateService.delete(code);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Certificate with code " + code + " was deleted successfully.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Certificate with code " + code + " was not found.");
        }
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CertificateRequest request){
        try{
            if (request.getName() == null || request.getName().isEmpty()) {
                return new ResponseEntity<>("Certificate name is required", HttpStatus.BAD_REQUEST);
            }
            if (request.getCode() == null || request.getCode().isEmpty()) {
                return new ResponseEntity<>("Certificate code is required", HttpStatus.BAD_REQUEST);
            }
            if (request.getBegin() == null) {
                return new ResponseEntity<>("Certificate begin is required", HttpStatus.BAD_REQUEST);
            }
            if (request.getEnd() == null) {
                return new ResponseEntity<>("Certificate end is required", HttpStatus.BAD_REQUEST);
            }
            CertificateResponse response = certificateService.addCertificate(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/update/{code}")
    public ResponseEntity<?> update(@RequestBody CertificateRequest request, @PathVariable String code){
        try {
            CertificateResponse update = certificateService.update(code, request);
            return new ResponseEntity<>(update, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
