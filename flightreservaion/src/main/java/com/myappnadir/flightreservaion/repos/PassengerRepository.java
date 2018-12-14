package com.myappnadir.flightreservaion.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myappnadir.flightreservaion.entities.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
