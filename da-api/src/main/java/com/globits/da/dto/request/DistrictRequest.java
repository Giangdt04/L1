package com.globits.da.dto.request;

import java.util.List;

public class DistrictRequest {
    private Integer id;
    private String code;
    private String name;
    private Integer provinceId;
    private List<CommuneRequest> communeRequests;

    public DistrictRequest() {
    }

    public DistrictRequest(Integer id, String code, String name, Integer provinceId, List<CommuneRequest> communeRequests) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.provinceId = provinceId;
        this.communeRequests = communeRequests;
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

    public List<CommuneRequest> getCommuneRequests() {
        return communeRequests;
    }

    public void setCommuneRequests(List<CommuneRequest> communeRequests) {
        this.communeRequests = communeRequests;
    }
}
