package com.globits.da.dto.response;

import com.globits.da.domain.Employee;
import com.globits.da.domain.address.Province;

import java.util.List;

public class ProvinceResponse {
    private Integer id;
    private String code;
    private String name;

    public ProvinceResponse() {
    }

    public ProvinceResponse(Integer id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
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

}
