package com.example.demo.domains;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Transient
    private Set<PublicTransports> publicTransports;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private Geolocation geolocation;

    public Set<Route> getRoutes() {
        return routes;
    }

    public Set<PublicTransports> getPublicTransports() {
        return publicTransports;
    }

    public void setPublicTransports(Set<PublicTransports> publicTransports) {
        this.publicTransports = publicTransports;
    }

    public Geolocation getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(Geolocation geolocation) {
        this.geolocation = geolocation;
    }

    public Set<StopStations> getStopStations() {
        return stopStations;
    }

    public void setStopStations(Set<StopStations> stopStations) {
        this.stopStations = stopStations;
    }

    public void setRoutes(Set<Route> routes) {
        this.routes = routes;
    }

    @Transient
    private Set<Route> routes;
    @Transient
    private Set<StopStations> stopStations;

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "city{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
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

    public City(Long id, String name
    ) {
        this.id = id;
        this.name = name;

    }

}
