package com.myappnadir.flightreservaion.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myappnadir.flightreservaion.entities.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
