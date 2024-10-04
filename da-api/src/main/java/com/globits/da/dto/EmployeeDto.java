package com.globits.da.dto;

import com.globits.da.domain.Employee;
import com.globits.da.exception.AppException;
import com.globits.da.exception.ErrorCode;
import lombok.Builder;

import javax.validation.constraints.*;

public class EmployeeDto {
    private Integer id;

    @NotEmpty(message = "Code is required and cannot be empty.")
    @Size(min = 6, max = 10, message = "Code must be between 6 and 10 characters.")
    private String code;

    @NotEmpty(message = "Name is required.")
    private String name;

    @NotEmpty(message = "Email is required.")
    @Email(message = "Email should be valid.")
    private String email;

    @NotEmpty(message = "Phone is required.")
    @Pattern(regexp = "\\d+", message = "Phone number can only contain digits.")
    @Size(max = 11, message = "Phone number must be at most 11 characters.")
    private String phone;

    @Min(value = 0, message = "Age cannot be negative.")
    private Integer age;
    @NotEmpty(message = "provinceId is required")
    private Integer provinceId;
    @NotEmpty(message = "districtId is required")
    private Integer districtId;
    @NotEmpty(message = "communeId is required")
    private Integer communeId;

    public EmployeeDto() {
    }

    public EmployeeDto(Integer id, @NotEmpty(message = "Code is required and cannot be empty.") @Size(min = 6, max = 10, message = "Code must be between 6 and 10 characters.") String code, @NotEmpty(message = "Name is required.") String name, @NotEmpty(message = "Email is required.") @Email(message = "Email should be valid.") String email, @NotEmpty(message = "Phone is required.") @Pattern(regexp = "\\d+", message = "Phone number can only contain digits.") @Size(max = 11, message = "Phone number must be at most 11 characters.") String phone, @Min(value = 0, message = "Age cannot be negative.") Integer age, @NotEmpty(message = "provinceId is required") Integer provinceId, @NotEmpty(message = "districtId is required") Integer districtId, @NotEmpty(message = "communeId is required") Integer communeId) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.provinceId = provinceId;
        this.districtId = districtId;
        this.communeId = communeId;
    }

    public EmployeeDto(Employee employee) {
        if(employee != null){
            this.id = employee.getId();
            this.code = employee.getCode();
            this.name = employee.getName();
            this.email = employee.getEmail();
            this.phone = employee.getPhone();
            this.age = employee.getAge();
            this.provinceId = employee.getProvince().getId();
            this.districtId = employee.getDistrict().getId();
            this.communeId = employee.getCommune().getId();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getCommuneId() {
        return communeId;
    }

    public void setCommuneId(Integer communeId) {
        this.communeId = communeId;
    }
}
