package com.globits.da.service.impl;

import com.globits.da.domain.Certificate;
import com.globits.da.domain.Employee;
import com.globits.da.domain.address.Commune;
import com.globits.da.domain.address.District;
import com.globits.da.domain.address.Province;
import com.globits.da.dto.request.CertificateRequest;
import com.globits.da.dto.request.CommuneRequest;
import com.globits.da.dto.response.CertificateResponse;
import com.globits.da.dto.response.CommuneResponse;
import com.globits.da.exception.AppException;
import com.globits.da.exception.ErrorCode;
import com.globits.da.repository.CertificateRepository;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public List<CertificateResponse> getAll() {
        List<CertificateResponse> list = certificateRepository.getAll();
        return list;
    }

    @Override
    public Optional<Certificate> search(String code) {
        Optional<Certificate> optional = certificateRepository.findCertificateByCode(code);
        if(optional.isPresent()){
            return optional;
        }else{
            throw new RuntimeException("Province not found with code: " + code);
        }
    }

    @Override
    public boolean delete(String code) {
        Optional<Certificate> optional = certificateRepository.findCertificateByCode(code);
        if(optional.isPresent()){
            certificateRepository.delete(optional.get());
            return true;
        }else{
            return false;
        }
    }

    @Override
    @Transactional
    public CertificateResponse addCertificate(CertificateRequest request) {
        Integer employeeId = request.getEmployeeId();
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));

        Province province = provinceRepository.findById(request.getProvinceId())
                .orElseThrow(() -> new AppException(ErrorCode.PROVINCE_NOT_FOUND));

        // Kiểm tra xem nhân viên đã có văn bằng A của tỉnh X chưa
        List<Certificate> existingCertificates = certificateRepository.findByEmployeeIdAndCodeAndProvinceId(employeeId, request.getCode(), request.getProvinceId());

        if (!existingCertificates.isEmpty()) {
            throw new AppException(ErrorCode.CERTIFICATE_EXISTS);
        }

        // Kiểm tra số lượng văn bằng cùng loại
        long count = certificateRepository.countByEmployeeIdAndCode(employeeId, request.getCode());

        if (count >= 3) {
            throw new AppException(ErrorCode.CERTIFICATE_LIMIT_REACHED);
        }
        // Tạo mới văn bằng
        Certificate certificate = new Certificate();
        if (request.getBegin() == null) {
            throw new AppException(ErrorCode.BEGIN_NOT_NULL);
        }
        certificate.setCode(request.getCode());
        certificate.setName(request.getName());
        certificate.setBegin(request.getBegin());
        certificate.setEnd(request.getEnd());

        LocalDate now = LocalDate.now();
        if (request.getEnd() != null && request.getEnd().isBefore(now)) {
            certificate.setStatus(false);
        } else {
            certificate.setStatus(request.isStatus());
        }

        certificate.setProvince(province); //Thiết lập mối quan hệ với tỉnh
        certificate.setEmployee(employee); // Thiết lập mối quan hệ với nhân viên

        Certificate savedCertificate = certificateRepository.save(certificate);
        return getCertificateResponse(request, savedCertificate); // Chuyển đổi sang phản hồi
    }

    @Override
    public CertificateResponse update(String code, CertificateRequest request) {
        Certificate certificate = certificateRepository.findCertificateByCode(code)
                .orElseThrow(() -> new RuntimeException("Certificate not found with code: " + code));
        return getCertificateResponse(request, certificate);
    }

    private CertificateResponse getCertificateResponse(CertificateRequest request, Certificate certificate){
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));

        Province province = provinceRepository.findById(request.getProvinceId())
                .orElseThrow(() -> new AppException(ErrorCode.PROVINCE_NOT_FOUND));
        if (request != null) {
            if (request.getCode() != null && !request.getCode().isEmpty()) {
                certificate.setCode(request.getCode());
            }
            if (request.getName() != null && !request.getName().isEmpty()) {
                certificate.setName(request.getName());
            }
            if (request.getBegin() != null) {
                certificate.setBegin(request.getBegin());
            }
            if (request.getEnd() != null) {
                certificate.setEnd(request.getEnd());
            }

            LocalDate now = LocalDate.now();
            if (request.getEnd() != null && request.getEnd().isBefore(now)) {
                certificate.setStatus(false);
            } else {
                certificate.setStatus(request.isStatus());
            }
            certificate.setProvince(province);
            certificate.setEmployee(employee);
        }

        Certificate savedCertificate = certificateRepository.save(certificate);

        return new CertificateResponse(
                savedCertificate.getId(),
                savedCertificate.getCode(),
                savedCertificate.getName(),
                savedCertificate.getBegin(),
                savedCertificate.getEnd(),
                savedCertificate.getStatus(),
                savedCertificate.getProvince().getId(),
                savedCertificate.getEmployee().getId()
        );
    }
}
