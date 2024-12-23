package com.globits.da.dto.request;

public class CommuneRequest {
    private Integer id;
    private String code;
    private String name;
    private Integer districtId;

    public CommuneRequest() {
    }

    public CommuneRequest(Integer id, String code, String name, Integer districtId) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.districtId = districtId;
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

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }
}
