package com.globits.da.repository;

import com.globits.da.domain.address.Province;
import com.globits.da.dto.response.ProvinceResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {

    @Query("select new com.globits.da.dto.response.ProvinceResponse(p.id, p.code, p.name) from Province p")
    List<ProvinceResponse> getAll();

    Optional<Province> findProvinceByCode(String code);

}
