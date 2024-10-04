package com.globits.da.service.impl;

import com.globits.da.domain.address.Commune;
import com.globits.da.domain.address.District;
import com.globits.da.domain.address.Province;
import com.globits.da.dto.request.CommuneRequest;
import com.globits.da.dto.response.CommuneResponse;
import com.globits.da.dto.response.DistrictResponse;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.service.CommuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommuneServiceImpl implements CommuneService{
    @Autowired
    private CommuneRepository communeRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public List<CommuneResponse> getAll() {
        List<CommuneResponse> list = communeRepository.getAll();
        return list;
    }

    @Override
    public Optional<Commune> search(String code) {
        Optional<Commune> optional = communeRepository.findCommuneByCode(code);
        if(optional.isPresent()){
            return optional;
        }else{
            throw new RuntimeException("Province not found with code: " + code);
        }
    }

    @Override
    public boolean delete(String code) {
        Optional<Commune> optional = communeRepository.findCommuneByCode(code);
        if(optional.isPresent()){
            communeRepository.delete(optional.get());
            return true;
        }else{
            return false;
        }
    }

    @Override
    public CommuneResponse create(CommuneRequest request) {
        Commune communeAdd = new Commune();
        return getCommuneResponse(request, communeAdd);
    }

    @Override
    public CommuneResponse update(String code, CommuneRequest request) {
        Commune commune = communeRepository.findCommuneByCode(code)
                .orElseThrow(() -> new RuntimeException("Commune not found with code: " + code));
        return getCommuneResponse(request, commune);
    }

    private CommuneResponse getCommuneResponse(CommuneRequest request, Commune commune){
        if (request.getCode() != null && !request.getCode().isEmpty()) {
            commune.setCode(request.getCode());
        }
        if (request.getName() != null && !request.getName().isEmpty()) {
            commune.setName(request.getName());
        }
        if (request.getDistrictId() != null) {
            District district = districtRepository.findById(request.getDistrictId())
                    .orElseThrow(() -> new RuntimeException("District not found with id: " + request.getDistrictId()));
            commune.setDistrict(district);
        }
        Commune newCommune = communeRepository.save(commune);
        return new CommuneResponse(newCommune.getId(),newCommune.getCode(),newCommune.getName(),newCommune.getDistrict().getId(),newCommune.getDistrict().getName());

    }
}
