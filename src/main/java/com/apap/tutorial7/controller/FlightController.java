package com.apap.tutorial7.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.service.FlightService;
import com.apap.tutorial7.service.PilotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;
    
    @Autowired
    private PilotService pilotService;


    @PostMapping(value = "/add")
    public FlightModel addFlightSubmit(@RequestBody FlightModel flight) {
        return flightService.addFlight(flight);
    }
    
    @GetMapping(value= "/view/{flightNumber}")
    private FlightModel viewFlight(@PathVariable ("flightNumber") String flightNumber) {
    	return flightService.getFlightDetailByFlightNumber(flightNumber).get();
    }
    
    @GetMapping(value= "/all")
    private List<FlightModel> viewAllFlights() {
    	return flightService.viewAllFlights();
    }
    
    @PutMapping(value = "/update/{flightId}")
    private String updateFlight(@PathVariable("flightId") long flightId, @RequestParam("destination") Optional<String> destination, 
    																	@RequestParam("origin") Optional<String> origin, 
    																	@RequestParam("time") @DateTimeFormat(iso = ISO.DATE) Optional<Date> time) {
    	FlightModel flight = flightService.getFlightDetailById(flightId).get();
        if (flight.equals(null)) {
        	return "Can't Find Your Flight";
        } else {
        	if(destination.isPresent()) {
        		flight.setDestination(destination.get());
        	}
        	if(origin.isPresent()) {
        		flight.setOrigin(origin.get());
        	}
        	if(time.isPresent()) {
        		flight.setTime(time.get());
        	}
        	flightService.updateFlightDetail(flightId, flight);
        	return "Flight Update Success";
        }
    }
    
  @DeleteMapping(value = "/delete/{flightId}")
  private String delete(@PathVariable("flightId") long flightId) {
	  flightService.deleteById(flightId);
      return "Flight Has Been Deleted";
  }
}