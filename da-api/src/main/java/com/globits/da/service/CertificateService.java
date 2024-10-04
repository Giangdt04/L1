package com.globits.da.service;

import com.globits.da.domain.Certificate;
import com.globits.da.domain.address.Commune;
import com.globits.da.dto.request.CertificateRequest;
import com.globits.da.dto.request.CommuneRequest;
import com.globits.da.dto.response.CertificateResponse;
import com.globits.da.dto.response.CommuneResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CertificateService {
    List<CertificateResponse> getAll();

    Optional<Certificate> search(String code);

    boolean delete(String code);

    CertificateResponse addCertificate(CertificateRequest request);

    CertificateResponse update(String code, CertificateRequest request);
}
