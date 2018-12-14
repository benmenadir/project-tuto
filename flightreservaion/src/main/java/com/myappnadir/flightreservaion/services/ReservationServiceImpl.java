package com.myappnadir.flightreservaion.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myappnadir.flightreservaion.dto.ReservationRequest;
import com.myappnadir.flightreservaion.entities.Flight;
import com.myappnadir.flightreservaion.entities.Passenger;
import com.myappnadir.flightreservaion.entities.Reservation;
import com.myappnadir.flightreservaion.repos.FlightRepository;
import com.myappnadir.flightreservaion.repos.PassengerRepository;
import com.myappnadir.flightreservaion.repos.ReservationRepository;
import com.myappnadir.flightreservaion.util.EmailUtil;
import com.myappnadir.flightreservaion.util.PDFGenerator;


@Service
public class ReservationServiceImpl implements ReservationService {
	@Value("${com.myappnadir.flightreservaion.itinerary.dirpath}")
	private String ITINERARY_DIR;

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	ReservationRepository reservationRpository;
	
	@Autowired
	PDFGenerator pdfGenerator;
	
	@Autowired
	EmailUtil emailUtil;
	
	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {
		//Make payment
		LOGGER.info("Inside bookFlight()"); 
		long flightId = request.getFlightId();
		LOGGER.info("Fetching flight for flight id :"+flightId); 
		Flight flight = flightRepository.findById(flightId).orElseThrow(null);
		 
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setEmail(request.getPassengerEmail());
		passenger.setPhone(request.getPassengerPhone());
		LOGGER.info("Saving the Passenger : " + passenger); 

		Passenger SavedPassenger = passengerRepository.save(passenger);		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(SavedPassenger);
		reservation.setCheckedIn(false);
		
		LOGGER.info("Saving the Reservation : " + reservation);
		Reservation savedReservation = reservationRpository.save(reservation);
		
		String filePath = ITINERARY_DIR + savedReservation.getId()+".pdf";
		LOGGER.info("Generating Itinerary"); 
		pdfGenerator.generateItinerary(savedReservation, filePath);
		
		LOGGER.info("Emailing the Itinerary"); 
		emailUtil.sendItinerary(passenger.getEmail(), filePath);
		
		return savedReservation;
	}

}
