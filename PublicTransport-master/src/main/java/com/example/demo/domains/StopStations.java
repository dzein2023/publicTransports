package com.example.demo.domains;


import jakarta.persistence.*;

@Entity
@Table(name="station")
public class StopStations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    private String stationName;

    public Geolocation getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(Geolocation geolocation) {
        this.geolocation = geolocation;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "geolocation_id")
    private Geolocation geolocation;
    @JoinColumn(name="city_id")
    private Long cityId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }


    public StopStations(Long id, String stationName
    ) {
        this.id = id;
        this.stationName = stationName;
    }

    @Override
    public String toString() {
        return "StopStations{" +
                "id=" + id +
                ", stationName='" + stationName + '\'' +
                '}';
    }
}
