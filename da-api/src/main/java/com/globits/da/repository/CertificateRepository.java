package com.globits.da.repository;

import com.globits.da.domain.Certificate;
import com.globits.da.dto.response.CertificateResponse;
import com.globits.da.dto.response.CommuneResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate,Integer> {
    @Query("select new com.globits.da.dto.response.CertificateResponse(p.id, p.code, p.name,p.begin, p.end,p.status, p.province.id, p.employee.id) from Certificate p")
    List<CertificateResponse> getAll();

    Optional<Certificate> findCertificateByCode(String code);

    List<Certificate> findByEmployeeIdAndCodeAndProvinceId(Integer employeeId, String code, Integer provinceId);

    long countByEmployeeIdAndCode(Integer employeeId, String code);
}
