package com.globits.da.rest;

import com.globits.da.dto.EmployeeDto;
import com.globits.da.dto.response.ApiResponse;
import com.globits.da.dto.search.EmployeeSearchDto;
import com.globits.da.exception.AppException;
import com.globits.da.exception.ErrorCode;
import com.globits.da.service.EmployeeService;
import com.globits.da.service.ExcelService;
import com.globits.da.service.impl.ExcelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class RestEmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ExcelService excelService;

    @GetMapping("/employees")
    public ResponseEntity<?> getAll() {
        List<EmployeeDto> list = employeeService.getAllEmployee();
        if (list.isEmpty()) {
            throw new AppException(ErrorCode.NO_EMPLOYEES_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestBody EmployeeSearchDto searchDto) {
        List<EmployeeDto> list = employeeService.searchEmployees(searchDto);
        if (list.isEmpty()) {
            throw new AppException(ErrorCode.NO_EMPLOYEES_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/deleteByCode/{code}")
    public ResponseEntity<String> delete(@PathVariable String code) {
        boolean isDeleted = employeeService.deleteEmployee(code);
        if (isDeleted) {
            return ResponseEntity.ok("Employee with code " + code + " was deleted successfully.");
        } else {
            throw new AppException(ErrorCode.NO_EMPLOYEES_FOUND);
        }
    }

    @GetMapping("/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        List<EmployeeDto> list = employeeService.getAllEmployee();
        if (list == null || list.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return;
        }
        // tạo ExcelServiceImpl với employee list
        excelService = new ExcelServiceImpl(list);
        excelService.exportToExcel(response);
    }

    @PostMapping("/add")
    public ApiResponse<EmployeeDto> addEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        return ApiResponse.<EmployeeDto>builder()
                .result(employeeService.createEmployee(employeeDto))
                .build();
    }

    @PutMapping("/update/{code}")
    public ApiResponse<EmployeeDto> updateEmployee(@PathVariable String code, @Valid @RequestBody EmployeeDto employeeDto) {
        EmployeeDto updateEmployee = employeeService.updateEmployee(code, employeeDto);
        return ApiResponse.<EmployeeDto>builder()
                .result(updateEmployee)
                .build();
    }

    @PostMapping("/import")
    public ResponseEntity<?> importEmployees(@RequestParam("file") MultipartFile file) {
        employeeService.saveEmployeesToDatabase(file);
        return ResponseEntity.ok("Employees data upload and saved to database successfully");
    }
}
