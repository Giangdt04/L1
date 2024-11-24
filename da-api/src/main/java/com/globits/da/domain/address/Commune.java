package com.globits.da.domain.address;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COMMUNE")
public class Commune extends AbtractAddress{
    @ManyToOne
    @JsonManagedReference
    private District district;

    public Commune() {
    }

    public Commune(Integer id, String code, String name, District district) {
        super(id, code, name);
        this.district = district;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
}
