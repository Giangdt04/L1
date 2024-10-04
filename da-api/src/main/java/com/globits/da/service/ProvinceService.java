package com.globits.da.service;

import com.globits.da.domain.address.Province;
import com.globits.da.dto.request.ProvinceRequest;
import com.globits.da.dto.response.ProvinceResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProvinceService {
    List<ProvinceResponse> getAll();

    Optional<Province> search(String code);

    boolean delete(String code);

    ProvinceResponse create(ProvinceRequest request);

    ProvinceResponse update(String code, ProvinceRequest request);

    ProvinceResponse createProvinceWithDistricts(ProvinceRequest request);

    ProvinceResponse updateProvinceWithDistricts(String provinceCode, ProvinceRequest request);

    ProvinceResponse createProvinceWithDistrictsAndCommunes(ProvinceRequest request);
}
