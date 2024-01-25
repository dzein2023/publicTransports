package com.example.demo.domains;

import jakarta.persistence.*;

@Entity
@Table(name = "route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String routeName;

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    @JoinColumn(name = "city_id")
    private Long city;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }


    public Route(Long id, String routeName
    ) {
        this.id = id;
        this.routeName = routeName;

    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", routeName='" + routeName + '\'' +
                '}';
    }
}
