package com.myappnadir.flightreservaion.services;

import com.myappnadir.flightreservaion.dto.ReservationRequest;
import com.myappnadir.flightreservaion.entities.Reservation;

public interface ReservationService {
	public Reservation bookFlight(ReservationRequest request) ; 

}
