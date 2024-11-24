package com.globits.da.service.impl;

import com.globits.da.domain.address.Commune;
import com.globits.da.domain.address.District;
import com.globits.da.domain.address.Province;
import com.globits.da.dto.request.CommuneRequest;
import com.globits.da.dto.request.DistrictRequest;
import com.globits.da.dto.response.DistrictResponse;
import com.globits.da.dto.response.ProvinceResponse;
import com.globits.da.exception.AppException;
import com.globits.da.exception.ErrorCode;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CommuneRepository communeRepository;

    @Override
    public List<DistrictResponse> getAll() {
        List<DistrictResponse> list = districtRepository.getAll();
        return list;
    }

    @Override
    public Optional<District> search(String code) {
        Optional<District> optional = districtRepository.findDistrictByCode(code);
        if(optional.isPresent()){
            return optional;
        }else{
            throw new RuntimeException("Province not found with code: " + code);
        }
    }

    @Override
    public boolean delete(String code) {
        Optional<District> optional = districtRepository.findDistrictByCode(code);
        if(optional.isPresent()){
            districtRepository.delete(optional.get());
            return true;
        }else{
            return false;
        }
    }

    @Override
    public DistrictResponse create(DistrictRequest request) {
        District districtAdd = new District();
        return getDistrictResponse(request, districtAdd);
    }

    @Override
    public DistrictResponse update(String code, DistrictRequest request) {
        District district = districtRepository.findDistrictByCode(code)
                .orElseThrow(() -> new RuntimeException("District not found with code: " + code));

        return getDistrictResponse(request, district);
    }

    @Override
    public List<DistrictResponse> findDistrictsByProvinceId(Integer provinceId) {
        List<District> districts = districtRepository.findDistrictsByProvinceId(provinceId);
        return districts.stream()
                .map(district -> new DistrictResponse(district.getId(), district.getCode(), district.getName(), district.getProvince().getId(), district.getProvince().getName()))
                .collect(Collectors.toList());
    }

    @Override
    public DistrictResponse createDistrictWithCommunes(DistrictRequest request) {
        if(request == null){
            throw new IllegalArgumentException("DistrictRequest cannot be null");
        }
        if(request.getCommuneRequests() == null){
            throw new IllegalArgumentException("CommuneRequests cannot be null");
        }

        Province province = provinceRepository.findById(request.getProvinceId())
                .orElseThrow(() -> new RuntimeException("Province not found with id: " + request.getProvinceId()));

        District district = new District();
        district.setCode(request.getCode());
        district.setName(request.getName());
        district.setProvince(province);

        List<Commune> communes = request.getCommuneRequests().stream()
                .filter(c -> c != null && c.getCode() != null && c.getName() != null)
                .map(c ->{
                    Commune commune = new Commune();
                    commune.setCode(c.getCode());
                    commune.setName(c.getName());
                    commune.setDistrict(district); // liên kết xã với huyện
                    return commune;
                })
                .collect(Collectors.toList());

        district.setCommunes(communes);

        District saveDistrict = districtRepository.save(district);

        return new DistrictResponse(saveDistrict.getId(), saveDistrict.getCode(), saveDistrict.getName(), saveDistrict.getProvince().getId(), saveDistrict.getProvince().getName());
    }

    @Override
    public DistrictResponse updateDistrictWithCommunes(String districtCode, DistrictRequest request) {
        if(request == null){
            throw new IllegalArgumentException("DistrictRequest cannot be null");
        }
        if(request.getCommuneRequests() == null){
            throw new IllegalArgumentException("CommuneRequests cannot be null");
        }

        District district = districtRepository.findDistrictByCode(districtCode)
                .orElseThrow(() -> new RuntimeException("District not found with code: " + districtCode));

        if (request.getCode() != null && !request.getCode().isEmpty()) {
            district.setCode(request.getCode());
        }
        if (request.getName() != null && !request.getName().isEmpty()) {
            district.setName(request.getName());
        }

        List<Commune> updateCommunes = new ArrayList<>();
        if(request.getCommuneRequests() != null){
            for (CommuneRequest communeRequest : request.getCommuneRequests()){
                if(communeRequest != null && communeRequest.getCode() != null){
                    Commune commune = communeRepository.findCommuneByCode(communeRequest.getCode())
                            .orElseThrow(() -> new AppException(ErrorCode.COMMUNE_NOT_FOUND));

                    if(communeRequest.getName() != null){
                        commune.setName(communeRequest.getName());
                    }

                    commune.setDistrict(district); // duy trì liên kết với district
                    updateCommunes.add(commune);
                }
            }
        }

        district.setCommunes(updateCommunes);

        District saveDistrict = districtRepository.save(district);

        return new DistrictResponse(saveDistrict.getId(), saveDistrict.getCode(), saveDistrict.getName(), saveDistrict.getProvince().getId(), saveDistrict.getProvince().getName());

    }

    private DistrictResponse getDistrictResponse(DistrictRequest request, District district) {
        if (request.getCode() != null && !request.getCode().isEmpty()) {
            district.setCode(request.getCode());
        }
        if (request.getName() != null && !request.getName().isEmpty()) {
            district.setName(request.getName());
        }
        if (request.getProvinceId() != null) {
            Province province = provinceRepository.findById(request.getProvinceId())
                    .orElseThrow(() -> new RuntimeException("Province not found with id: " + request.getProvinceId()));
            district.setProvince(province);
        }
        District newDistrict = districtRepository.save(district);
        return new DistrictResponse(newDistrict.getId(),newDistrict.getCode(),newDistrict.getName(),newDistrict.getProvince().getId(),newDistrict.getProvince().getName());
    }
}
