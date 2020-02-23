package com.apap.tu04.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.apap.tu04.model.FlightModel;
import org.springframework.stereotype.Repository;

/**
 * FlightDb
 * 
 * @author Dwi Ana Ambar Rofiqoh
 *
 */

public interface FlightDb extends JpaRepository<FlightModel, Long>{
	FlightModel findByFlightNumber(String flightNumber);
}
