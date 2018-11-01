package com.apap.tutorial7.service;

import java.util.Optional;
import java.util.List;

import com.apap.tutorial7.model.FlightModel;

/**
 * FlightService
 */
public interface FlightService {
    FlightModel addFlight(FlightModel flight);
    
    void deleteByFlightNumber(String flightNumber);
    
    void deleteById(long id);

    Optional<FlightModel> getFlightDetailByFlightNumber(String flightNumber);

	List<FlightModel> viewAllFlights();

	Optional<FlightModel> getFlightDetailById(long id);

	void updateFlightDetail(long id, FlightModel flights);
}