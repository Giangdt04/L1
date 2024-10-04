package com.globits.da.repository;

import com.globits.da.domain.address.Commune;
import com.globits.da.domain.address.District;
import com.globits.da.dto.response.CommuneResponse;
import com.globits.da.dto.response.DistrictResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommuneRepository extends JpaRepository<Commune,Integer> {
    @Query("select new com.globits.da.dto.response.CommuneResponse(p.id, p.code, p.name,p.district.id, p.district.name) from Commune p")
    List<CommuneResponse> getAll();

    Optional<Commune> findCommuneByCode(String code);
}
