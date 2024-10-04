package com.globits.da.domain.address;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DISTRICT")
public class District extends AbtractAddress{
    @ManyToOne
    private Province province;

    @OneToMany(mappedBy = "district")
    private List<Commune> communes = new ArrayList<>();

    public District() {
    }

    public District(Integer id, String code, String name, Province province, List<Commune> communes) {
        super(id, code, name);
        this.province = province;
        this.communes = communes;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public List<Commune> getCommunes() {
        return communes;
    }

    public void setCommunes(List<Commune> communes) {
        this.communes = communes;
    }
}
