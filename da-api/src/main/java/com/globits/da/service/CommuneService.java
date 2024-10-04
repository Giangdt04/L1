package com.globits.da.service;

import com.globits.da.domain.address.Commune;
import com.globits.da.domain.address.District;
import com.globits.da.dto.request.CommuneRequest;
import com.globits.da.dto.request.DistrictRequest;
import com.globits.da.dto.response.CommuneResponse;
import com.globits.da.dto.response.DistrictResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CommuneService {
    List<CommuneResponse> getAll();

    Optional<Commune> search(String code);

    boolean delete(String code);

    CommuneResponse create(CommuneRequest request);

    CommuneResponse update(String code, CommuneRequest request);
}
