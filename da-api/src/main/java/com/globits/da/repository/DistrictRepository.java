package com.globits.da.repository;

import com.globits.da.domain.address.District;
import com.globits.da.domain.address.Province;
import com.globits.da.dto.response.DistrictResponse;
import com.globits.da.dto.response.ProvinceResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    @Query("select new com.globits.da.dto.response.DistrictResponse(p.id, p.code, p.name,p.province.id, p.province.name) from District p")
    List<DistrictResponse> getAll();

    Optional<District> findDistrictByCode(String code);

    List<District> deleteDistrictsByProvinceId(Integer id);

    @Query("SELECT d FROM District d WHERE d.province.id = :provinceId")
    List<District> findDistrictsByProvinceId(@Param("provinceId") Integer provinceId);
}
