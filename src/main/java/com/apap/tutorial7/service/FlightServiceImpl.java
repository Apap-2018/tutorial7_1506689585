package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.repository.FlightDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * FlightServiceImpl
 */
@Service
@Transactional
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightDb flightDb;
    
    @Override
    public FlightModel addFlight(FlightModel flight) {
        return flightDb.save(flight);
    }

    @Override
    public void deleteByFlightNumber(String flightNumber) {
        flightDb.deleteByFlightNumber(flightNumber);
    }

    @Override
    public Optional<FlightModel> getFlightDetailByFlightNumber(String flightNumber) {
        return flightDb.findByFlightNumber(flightNumber);
    }
    
	@Override
	public Optional<FlightModel> getFlightDetailById(long id) {
		return flightDb.findById(id);
	}

	@Override
	public List<FlightModel> viewAllFlights() {
		return flightDb.findAll();
	}

	@Override
	public void updateFlightDetail(long id, FlightModel flights) {
		FlightModel allFlight = flightDb.getOne(id);
		allFlight.setOrigin(flights.getOrigin());
		allFlight.setDestination(flights.getDestination());
		allFlight.setTime(flights.getTime());
	}

	@Override
	public void deleteById(long id) {
		flightDb.deleteById(id);
	}
}