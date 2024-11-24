package com.globits.da.service.impl;

import com.globits.da.domain.address.Commune;
import com.globits.da.domain.address.District;
import com.globits.da.domain.address.Province;
import com.globits.da.dto.request.CommuneRequest;
import com.globits.da.dto.request.DistrictRequest;
import com.globits.da.dto.request.ProvinceRequest;
import com.globits.da.dto.response.ProvinceResponse;
import com.globits.da.exception.AppException;
import com.globits.da.exception.ErrorCode;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public List<ProvinceResponse> getAll() {
        List<ProvinceResponse> provinceDtos = provinceRepository.getAll();
        return provinceDtos;
    }

    @Override
    public Optional<Province> search(String code) {
        return provinceRepository.findProvinceByCode(code);
    }


    @Override
    @Transactional
    public boolean delete(String code) {
        Optional<Province> optional = provinceRepository.findProvinceByCode(code);
        if (optional.isPresent()) {
            Province province = optional.get();
            districtRepository.deleteDistrictsByProvinceId(province.getId());
            provinceRepository.delete(province);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ProvinceResponse create(ProvinceRequest request) {
        Province province = new Province();
        province.setCode(request.getCode());
        province.setName(request.getName());

        Province savedProvince = provinceRepository.save(province);
        return new ProvinceResponse(savedProvince.getId(), savedProvince.getCode(), savedProvince.getName());
    }

    @Override
    public ProvinceResponse update(String code, ProvinceRequest request) {
        // Tìm tỉnh theo mã code
        Province province = provinceRepository.findProvinceByCode(code)
                .orElseThrow(() -> new AppException(ErrorCode.CODE_NOT_EXISTED));

        // Cập nhật thông tin tỉnh
        if (request.getCode() != null && !request.getCode().isEmpty()) {
            province.setCode(request.getCode());
        }
        if (request.getName() != null && !request.getName().isEmpty()) {
            province.setName(request.getName());
        }

        // Lưu lại thông tin đã cập nhật
        Province updatedProvince = provinceRepository.save(province);

        // Trả về thông tin sau khi cập nhật thành công
        return new ProvinceResponse(updatedProvince.getId(), updatedProvince.getCode(), updatedProvince.getName());
    }

    @Override
    @Transactional
    public ProvinceResponse createProvinceWithDistricts(ProvinceRequest request) {
        if (request == null) {
            throw new AppException(ErrorCode.REQUEST_NOT_NULL);
        }
        if (request.getDistrictRequests() == null) {
            throw new AppException(ErrorCode.REQUEST_NOT_NULL);
        }

        // Tạo mới tỉnh
        Province province = new Province();
        province.setCode(request.getCode());
        province.setName(request.getName());

        // Thêm danh sách huyện
        List<District> districts = request.getDistrictRequests().stream()
                .filter(d -> d != null && d.getCode() != null && d.getName() != null) // Kiểm tra null
                .map(d -> {
                    District district = new District();
                    district.setCode(d.getCode());
                    district.setName(d.getName());
                    district.setProvince(province); // Liên kết huyện với tỉnh
                    return district;
                })
                .collect(Collectors.toList());

        province.setDistricts(districts); // Gắn danh sách huyện vào tỉnh

        // Lưu đồng thời tỉnh và các huyện
        Province savedProvince = provinceRepository.save(province);

        // Trả về response sau khi lưu thành công
        return new ProvinceResponse(savedProvince.getId(), savedProvince.getCode(), savedProvince.getName());

    }

    @Override
    @Transactional
    public ProvinceResponse updateProvinceWithDistricts(String provinceCode, ProvinceRequest request) {
        // Kiểm tra xem request có null không
        if (request == null) {
            throw new AppException(ErrorCode.REQUEST_NOT_NULL);
        }

        // Tìm tỉnh theo mã code
        Province province = provinceRepository.findProvinceByCode(provinceCode)
                .orElseThrow(() -> new AppException(ErrorCode.CODE_NOT_EXISTED));

        // Cập nhật thông tin tỉnh
        if (request.getCode() != null && !request.getCode().isEmpty()) {
            province.setCode(request.getCode());
        }
        if (request.getName() != null && !request.getName().isEmpty()) {
            province.setName(request.getName());
        }

        // Cập nhật danh sách huyện
        List<District> updatedDistricts = new ArrayList<>();

        if (request.getDistrictRequests() != null) {
            for (DistrictRequest districtRequest : request.getDistrictRequests()) {

                // Huyện đã tồn tại -> Sửa huyện
                District existingDistrict = districtRepository.findDistrictByCode(districtRequest.getCode())
                        .orElseThrow(() -> new AppException(ErrorCode.CODE_NOT_EXISTED));

                existingDistrict.setCode(districtRequest.getCode());
                existingDistrict.setName(districtRequest.getName());
                updatedDistricts.add(existingDistrict);

            }
        }else{
            throw new AppException(ErrorCode.REQUEST_NOT_NULL);
        }

        // Gán lại danh sách huyện cho tỉnh
        province.setDistricts(updatedDistricts);

        // Lưu tỉnh và danh sách huyện đã cập nhật
        Province updatedProvince = provinceRepository.save(province);

        // Trả về thông tin tỉnh đã cập nhật
        return new ProvinceResponse(updatedProvince.getId(), updatedProvince.getCode(), updatedProvince.getName());

    }

    @Override
    public ProvinceResponse createProvinceWithDistrictsAndCommunes(ProvinceRequest request) {
        if (request == null) {
            throw new AppException(ErrorCode.REQUEST_NOT_NULL);
        }
        if (request.getDistrictRequests() == null) {
            throw new AppException(ErrorCode.REQUEST_NOT_NULL);
        }

        // Tạo mới tỉnh
        Province province = new Province();
        province.setCode(request.getCode());
        province.setName(request.getName());

        // Thêm danh sách huyện và xã
        List<District> districts = new ArrayList<>();

        for (DistrictRequest districtRequest : request.getDistrictRequests()) {
            District district = new District();
            district.setCode(districtRequest.getCode());
            district.setName(districtRequest.getName());
            district.setProvince(province); // Liên kết huyện với tỉnh

            // Thêm danh sách xã cho huyện
            List<Commune> communes = new ArrayList<>();
            for (CommuneRequest communeRequest : districtRequest.getCommuneRequests()) {
                Commune commune = new Commune();
                commune.setCode(communeRequest.getCode());
                commune.setName(communeRequest.getName());
                commune.setDistrict(district); // Liên kết xã với huyện
                communes.add(commune);
            }

            district.setCommunes(communes); // Gắn danh sách xã vào huyện
            districts.add(district); // Thêm huyện vào danh sách
        }

        province.setDistricts(districts); // Gắn danh sách huyện vào tỉnh

        // Lưu đồng thời tỉnh và các huyện
        Province savedProvince = provinceRepository.save(province);

        // Trả về response sau khi lưu thành công
        return new ProvinceResponse(savedProvince.getId(), savedProvince.getCode(), savedProvince.getName());
    }


}
