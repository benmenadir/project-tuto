package com.myappnadir.flightreservaion.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myappnadir.flightreservaion.dto.ReservationRequest;
import com.myappnadir.flightreservaion.entities.Flight;
import com.myappnadir.flightreservaion.entities.Reservation;
import com.myappnadir.flightreservaion.repos.FlightRepository;
import com.myappnadir.flightreservaion.services.ReservationService;

@Controller
public class ReservationController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	ReservationService reservationService;
	
	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") long flightId, ModelMap modelMap) {
		LOGGER.info("Inside showCompleteReservation invoked with the flight Id"+flightId );
		Flight flight = flightRepository.findById(flightId).orElseThrow(null);
		modelMap.addAttribute("flight",flight);
		LOGGER.info("Flight is:"+flight);
		return "completeReservation";
	}
	
	@RequestMapping(value="/completeReservation", method=RequestMethod.POST)
	public String completeReservation(ReservationRequest request, ModelMap modelMap ) {
	Reservation reservation = 	reservationService.bookFlight(request);
	LOGGER.info("Inside completeReservation "+request );
	modelMap.addAttribute("msg", "Reservation created successfully and the id is" + reservation.getId());
		return "reservationConfirmation";
	}
}
