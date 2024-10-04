package com.globits.da.domain.address;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PROVINCE")
public class Province extends AbtractAddress {
    @OneToMany(mappedBy = "province")
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
