package com.example.demo.services;

import com.example.demo.domains.City;
import com.example.demo.domains.PublicTransports;
import com.example.demo.exceptions.ResourceCityException;
import com.example.demo.repositories.CityRepository;
import com.example.demo.repositories.PublicTransportationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private final PublicTransportService publicTransportService;
    private final PublicTransportationRepository publicTransportationRepository;

    public CityService(CityRepository cityRepository, PublicTransportService publicTransportService, PublicTransportationRepository publicTransportationRepository) {
        this.cityRepository = cityRepository;
        this.publicTransportService = publicTransportService;
        this.publicTransportationRepository = publicTransportationRepository;
    }

    public City addCity(City c)
    {
        City city=new City();
        city.setName(c.getName());
        city.setGeolocation(c.getGeolocation());
        City savedCity=cityRepository.save(city);
        Long cityId = savedCity.getId();
        if (savedCity.getPublicTransports() != null && !savedCity.getPublicTransports().isEmpty()) {
            List<PublicTransports> students = new ArrayList<>(c.getPublicTransports());
            students.forEach(publicTransport -> {
                publicTransportService.add(publicTransport, cityId);
            });
        }
        return savedCity;

    }

    public City updateCity(Long cityId,City c) {
        City oldCity=cityRepository.findById(cityId).orElseThrow(
                ()->new RuntimeException("city does not exist"));
        City city=new City();
        city.setName(c.getName());
        city.setGeolocation(c.getGeolocation());
        City savedCity=cityRepository.save(city);
        if (savedCity.getPublicTransports() != null && !savedCity.getPublicTransports().isEmpty()) {
           publicTransportService.removeAllPublicTransport(cityId);
        }
        if (savedCity.getPublicTransports() != null && !savedCity.getPublicTransports().isEmpty()) {
            List<PublicTransports> cities = new ArrayList<>(c.getPublicTransports());
           cities.forEach(publicTransport -> {
                publicTransportService.add(publicTransport, cityId);
            });
        }
        return cityRepository.saveAndFlush(c);

    }
    public void deleteCity(Long cityId){
        City c=cityRepository.findById(cityId)
                .orElseThrow(()->new RuntimeException("city does not exist"));
        Set<PublicTransports> publicTransportsOld=c.getPublicTransports();
        if (publicTransportsOld != null && !publicTransportsOld.isEmpty()) {
            publicTransportService.removeAllPublicTransport(cityId);
        }

    }

    public City get(Long id) {
        City city = cityRepository.findById(id).orElseThrow(
                () -> new ResourceCityException(
                        "city with id=" + id + " does not exist")
        );
        List<PublicTransports> publicTransports = publicTransportService.findAllPublicTransports(id);
       Set<PublicTransports> publicTransportsSet=new HashSet<>(publicTransports);
        if (!publicTransports.isEmpty() && publicTransports != null) {
           city.setPublicTransports(publicTransportsSet);
        } else {
            List<PublicTransports> publicTransportsList = new ArrayList<>();
            Set<PublicTransports> publicTransports1=new HashSet<>( publicTransportsList);
            city.setPublicTransports(publicTransports1);
        }
        return city;

    }
}
