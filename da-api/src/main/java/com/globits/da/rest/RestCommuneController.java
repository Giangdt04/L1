package com.globits.da.rest;

import com.globits.da.domain.address.Commune;
import com.globits.da.domain.address.District;
import com.globits.da.dto.request.CommuneRequest;
import com.globits.da.dto.request.DistrictRequest;
import com.globits.da.dto.response.CommuneResponse;
import com.globits.da.dto.response.DistrictResponse;
import com.globits.da.service.CommuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/commune")
public class RestCommuneController {
    @Autowired
    private CommuneService communeService;

    @GetMapping("/communes")
    public ResponseEntity<?> getAll(){
        List<CommuneResponse> list = communeService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/search/{code}")
    public ResponseEntity<?> search(@PathVariable String code){
        Optional<Commune> optional = communeService.search(code);
        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Commune not found with code: " + code, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<String> delete(@PathVariable String code){
        boolean isDeleted = communeService.delete(code);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Commune with code " + code + " was deleted successfully.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Commune with code " + code + " was not found.");
        }
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CommuneRequest request){
        try{
            if (request.getName() == null || request.getName().isEmpty()) {
                return new ResponseEntity<>("Commune name is required", HttpStatus.BAD_REQUEST);
            }
            if (request.getCode() == null || request.getCode().isEmpty()) {
                return new ResponseEntity<>("Commune code is required", HttpStatus.BAD_REQUEST);
            }
            CommuneResponse response = communeService.create(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/update/{code}")
    public ResponseEntity<?> update(@RequestBody CommuneRequest request, @PathVariable String code){
        try {
            CommuneResponse update = communeService.update(code, request);
            return new ResponseEntity<>(update, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
