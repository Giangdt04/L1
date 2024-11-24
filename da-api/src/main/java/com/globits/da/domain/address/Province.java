package com.globits.da.domain.address;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PROVINCE")
public class Province extends AbtractAddress {
    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    // sử dụng @JsonManagedReference(District) để bắt đầu mối quan hệ và @JsonBackReference để tránh việc lặp lại
    private List<District> districts = new ArrayList<>();

    public Province() {
    }

    public Province(Integer id, String code, String name, List<District> districts) {
        super(id, code, name);
        this.districts = districts;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
}
