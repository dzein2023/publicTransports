package com.example.demo.domains;

import jakarta.persistence.*;

@Entity
@Table(name = "transports")
public class PublicTransports {
    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type; // Bus, Train, etc.
    @JoinColumn(name = "city_id")
    private Long cityId;

    public PublicTransports() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public PublicTransports(Long id, String name, String type
    ) {
        this.id = id;
        this.name = name;
        this.type = type;

    }
}
