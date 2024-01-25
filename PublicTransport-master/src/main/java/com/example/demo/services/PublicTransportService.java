package com.example.demo.services;

import com.example.demo.domains.PublicTransports;
import com.example.demo.repositories.PublicTransportationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicTransportService<PublicTransport> {
    private final PublicTransportationRepository publicTransportationRepository;

    public PublicTransportService(PublicTransportationRepository publicTransportationRepository) {
        this.publicTransportationRepository = publicTransportationRepository;
    }

    public static <PublicTransports> PublicTransports addPublicTransport(PublicTransports c) {
    return c;
    }

    public PublicTransports add(PublicTransports publicTransport, Long cityId) {
        PublicTransports publicTransports=new PublicTransports();
        publicTransports.setName(publicTransport.getName());
        publicTransports.setType(publicTransport.getType());
        publicTransports.setCityId(cityId);
        return publicTransportationRepository.save(publicTransports);
    }
   public void removeAllPublicTransport(Long cityId){
     publicTransportationRepository.deleteAllPubicTransportsByCityId(cityId);
   }
   public List<PublicTransports> findAllPublicTransports(Long cityId){
        return publicTransportationRepository.findAllByCityId(cityId);
   }


}
