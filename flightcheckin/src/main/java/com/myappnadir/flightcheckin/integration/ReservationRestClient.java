package com.myappnadir.flightcheckin.integration;

import com.myappnadir.flightcheckin.integration.dto.Reservation;
import com.myappnadir.flightcheckin.integration.dto.ReservationUpdateRequest;

public interface ReservationRestClient {

	public Reservation findReservation(Long id);
	public Reservation updateReservation(ReservationUpdateRequest request);
	
}
 