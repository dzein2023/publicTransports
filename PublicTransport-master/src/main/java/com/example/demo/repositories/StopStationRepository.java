package com.example.demo.repositories;

import com.example.demo.domains.StopStations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopStationRepository extends JpaRepository<StopStations,Long> {
}
