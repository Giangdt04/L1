package com.globits.da.dto.response;

import com.globits.da.domain.address.District;

import java.util.List;

public class DistrictResponse{
    private Integer id;
    private String code;
    private String name;
    private Integer provinceId;
    private String provinceName;


    public DistrictResponse() {
    }


    public DistrictResponse(Integer id, String code, String name, Integer provinceId, String provinceName) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
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

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

}
