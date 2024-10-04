package com.globits.da.service.impl;

import com.globits.da.domain.Employee;
import com.globits.da.domain.address.Commune;
import com.globits.da.domain.address.District;
import com.globits.da.domain.address.Province;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.dto.search.EmployeeSearchDto;
import com.globits.da.exception.AppException;
import com.globits.da.exception.ErrorCode;
import com.globits.da.repository.*;
import com.globits.da.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private CommuneRepository communeRepository;


    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<EmployeeDto> employeeResponses = employeeRepository.getAll();
        return employeeResponses;
    }

    @Override
    public List<EmployeeDto> searchEmployees(EmployeeSearchDto searchDto) {
        return employeeRepository.searchEmployees(
                searchDto.getCode(),
                searchDto.getName(),
                searchDto.getAge(),
                searchDto.getEmail(),
                searchDto.getPhone()
        );
    }

    @Override
    public boolean deleteEmployee(String code) {
        Optional<Employee> optional = employeeRepository.findByCode(code);
        if (optional.isPresent()) {
            employeeRepository.delete(optional.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        if (employeeRepository.findByCode(employeeDto.getCode()).isPresent()) {
            throw new AppException(ErrorCode.CODE_ALREADY_EXISTS);
        }
        Employee employeeAdd = new Employee();
        return getEmployeeResponse(employeeDto, employeeAdd);
    }

    @Override
    public EmployeeDto updateEmployee(String code, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findByCode(code)
                .orElseThrow(() -> new AppException(ErrorCode.CODE_NOT_EXISTED));

        return getEmployeeResponse(employeeDto, employee);
    }

    @Override
    public void saveEmployeesToDatabase(MultipartFile file) {
        if(ExcelServiceImpl.importEmployees(file)){
            try {
                List<EmployeeDto> employeeDtos = ExcelServiceImpl.excelToEmployees(file.getInputStream());
                List<Employee>  employees = employeeDtos.stream()
                        .map(this::convertDtoToEntity) // chuyển Dto sang entity
                        .collect(Collectors.toList());

                employeeRepository.saveAll(employees);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public Employee convertDtoToEntity(EmployeeDto dto){
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setCode(dto.getCode());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setAge(dto.getAge());

        // Tìm và set các khóa ngoại (Province, District, Commune) từ repository
        Province province = provinceRepository.findById(dto.getProvinceId())
                .orElseThrow(() -> new AppException(ErrorCode.PROVINCE_NOT_FOUND));
        District district = districtRepository.findById(dto.getDistrictId())
                .orElseThrow(() -> new AppException(ErrorCode.DISTRICT_NOT_FOUND));
        Commune commune = communeRepository.findById(dto.getCommuneId())
                .orElseThrow(() -> new AppException(ErrorCode.COMMUNE_NOT_FOUND));

        employee.setProvince(province);
        employee.setDistrict(district);
        employee.setCommune(commune);

        return employee;
    }

    private EmployeeDto getEmployeeResponse(EmployeeDto employeeDto, Employee employee) {
        // Kiểm tra và sửa nếu có đủ thông tin Tỉnh, Huyện, Xã
        if (employeeDto.getCommuneId() != null && employeeDto.getDistrictId() != null && employeeDto.getProvinceId() != null) {
            Commune commune = communeRepository.findById(employeeDto.getCommuneId())
                    .orElseThrow(() -> new AppException(ErrorCode.CODE_NOT_EXISTED));

            District district = districtRepository.findById(employeeDto.getDistrictId())
                    .orElseThrow(() -> new AppException(ErrorCode.CODE_NOT_EXISTED));

            Province province = provinceRepository.findById(employeeDto.getProvinceId())
                    .orElseThrow(() -> new AppException(ErrorCode.CODE_NOT_EXISTED));

            // Kiểm tra mối quan hệ giữa Xã, Huyện, Tỉnh
            if (!commune.getDistrict().getId().equals(district.getId())) {
                throw new  AppException(ErrorCode.COMMUNE_NOT_EXISTS);
            }

            if (!district.getProvince().getId().equals(province.getId())) {
                throw new  AppException(ErrorCode.DISTRICT_NOT_EXISTS);
            }

            employee.setCommune(commune);
            employee.setDistrict(district);
            employee.setProvince(province);
        }

        // Sửa các trường khác
        employee.setCode(employeeDto.getCode());
        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setPhone(employeeDto.getPhone());
        employee.setAge(employeeDto.getAge());

        // Lưu employee
        Employee newEmployee = employeeRepository.save(employee);
        return new EmployeeDto(newEmployee.getId(), newEmployee.getCode(), newEmployee.getName(), newEmployee.getEmail(), newEmployee.getPhone(), newEmployee.getAge(), newEmployee.getProvince().getId(), newEmployee.getDistrict().getId(), newEmployee.getCommune().getId());
    }

}
