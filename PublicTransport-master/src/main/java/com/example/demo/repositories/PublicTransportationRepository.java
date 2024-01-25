package com.example.demo.repositories;

import com.example.demo.domains.PublicTransports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PublicTransportationRepository extends JpaRepository<PublicTransports, Long> {

    void deleteAllPubicTransportsByCityId(Long cityId);

    List<PublicTransports> findAllByCityId(long cityId);
}
