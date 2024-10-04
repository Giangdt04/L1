package com.globits.da.service;

import com.globits.da.domain.address.District;
import com.globits.da.domain.address.Province;
import com.globits.da.dto.request.DistrictRequest;
import com.globits.da.dto.request.ProvinceRequest;
import com.globits.da.dto.response.DistrictResponse;
import com.globits.da.dto.response.ProvinceResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DistrictService {
    List<DistrictResponse> getAll();

    Optional<District>  search(String code);

    boolean delete(String code);

    DistrictResponse create(DistrictRequest request);

    DistrictResponse update(String code, DistrictRequest request);

    List<DistrictResponse> findDistrictsByProvinceId(Integer provinceId);

    DistrictResponse createDistrictWithCommunes(DistrictRequest request);

    DistrictResponse updateDistrictWithCommunes(String districtCode, DistrictRequest request);
}
