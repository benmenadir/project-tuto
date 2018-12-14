package com.myappnadir.flightreservaion.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myappnadir.flightreservaion.dto.ReservationUpdateRequest;
import com.myappnadir.flightreservaion.entities.Reservation;
import com.myappnadir.flightreservaion.repos.ReservationRepository;

@RestController
public class ReservationRestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);

	@Autowired
	ReservationRepository reservationRepository;
	
	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {
		LOGGER.info("Inside findReservation() for Id : "+id);
		return reservationRepository.findById(id).orElseThrow(null);
	}
	
	@RequestMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		LOGGER.info("Inside updateReservation() for : " + request);

		Reservation reservation = reservationRepository.findById(request.getId()).orElseThrow(null);
		reservation.setCheckedIn(request.getCheckedIn());
		reservation.setNumberOfBags(request.getNumberOfBags());		
		LOGGER.info("Saving Reservation : "+ reservation);

		return reservationRepository.save(reservation);	
	}
}
