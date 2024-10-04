package com.globits.da.dto.request;

import com.globits.da.domain.address.District;
import com.globits.da.domain.address.Province;

import java.util.List;

public class ProvinceRequest {

    private String code;
    private String name;
    private List<DistrictRequest> districtRequests;

    public ProvinceRequest() {
    }

    public ProvinceRequest(String code, String name, List<DistrictRequest> districtRequests) {
        this.code = code;
        this.name = name;
        this.districtRequests = districtRequests;
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

    public List<DistrictRequest> getDistrictRequests() {
        return districtRequests;
    }

    public void setDistrictRequests(List<DistrictRequest> districtRequests) {
        this.districtRequests = districtRequests;
    }
}
