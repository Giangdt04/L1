package com.globits.da.service;

import com.globits.da.dto.EmployeeDto;
import com.globits.da.dto.search.EmployeeSearchDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface EmployeeService {
    List<EmployeeDto> getAllEmployee();

    List<EmployeeDto> searchEmployees(EmployeeSearchDto searchDto);

    boolean deleteEmployee(String code);

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto updateEmployee(String code, EmployeeDto employeeDto);

    void saveEmployeesToDatabase(MultipartFile file);
}
