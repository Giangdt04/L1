package com.globits.da.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.globits.da.domain.address.Province;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "CERTIFICATE")
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "CODE")
    private String code;
    @Column(name = "NAME")
    private String name;

    @Column(name = "BEGIN")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate begin;

    @Column(name = "END")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate end;

    @Column(name = "STATUS")
    private Boolean status;

    @ManyToOne
    private Province province;

    @ManyToOne
    private Employee employee;

    public Certificate() {
    }

    public Certificate(Integer id, String code, String name, LocalDate begin, LocalDate end, Boolean status, Province province, Employee employee) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.begin = begin;
        this.end = end;
        this.status = status;
        this.province = province;
        this.employee = employee;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
