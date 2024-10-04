package com.globits.da.dto.response;

public class CommuneResponse {
    private Integer id;
    private String code;
    private String name;
    private Integer districtId;
    private String districtName;

    public CommuneResponse() {
    }

    public CommuneResponse(Integer id, String code, String name, Integer districtId, String districtName) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.districtId = districtId;
        this.districtName = districtName;
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

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
