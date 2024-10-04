package com.globits.da.domain.address;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbtractAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "CODE")
    private String code;
    @Column(name = "NAME")
    private String name;

    public AbtractAddress() {
    }

    public AbtractAddress(Integer id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
