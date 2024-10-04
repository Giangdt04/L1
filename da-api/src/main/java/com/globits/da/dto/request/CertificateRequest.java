package com.globits.da.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;

public class CertificateRequest {
    private Integer id;

    @NotEmpty(message = "Code is required and cannot be empty.")
    @Size(min = 3, max = 50, message = "Code must be between 3 and 50 characters.")
    private String code;

    @NotEmpty(message = "Name is required and cannot be empty.")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters.")
    private String name;

    @NotNull(message = "Begin date is required.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate begin;

    @NotNull(message = "End date is required.")
    @Future(message = "End date must be in the future.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate end;

    @NotNull(message = "Status is required.")
    private Boolean status;

    @NotNull(message = "provinceId is required and cannot be empty.")
    private Integer provinceId;

    @NotNull(message = "employeeId is required and cannot be empty.")
    private Integer employeeId;

    public CertificateRequest() {
    }

    public CertificateRequest(Integer id, @NotEmpty(message = "Code is required and cannot be empty.") @Size(min = 3, max = 50, message = "Code must be between 3 and 50 characters.") String code, @NotEmpty(message = "Name is required and cannot be empty.") @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters.") String name, @NotNull(message = "Begin date is required.") LocalDate begin, @NotNull(message = "End date is required.") @Future(message = "End date must be in the future.") LocalDate end, @NotNull(message = "Status is required.") Boolean status, @NotEmpty(message = "provinceId is required and cannot be empty.") Integer provinceId, @NotEmpty(message = "employeeId is required and cannot be empty.") Integer employeeId) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.begin = begin;
        this.end = end;
        this.status = status;
        this.provinceId = provinceId;
        this.employeeId = employeeId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Boolean getStatus() {
        return status;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
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

    public LocalDate getBegin() {
        return begin;
    }

    public void setBegin(LocalDate begin) {
        this.begin = begin;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
