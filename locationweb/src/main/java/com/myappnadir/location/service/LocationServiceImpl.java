package com.myappnadir.location.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myappnadir.location.entities.Location;
import com.myappnadir.location.repos.LocationRepository;


@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository repository;
	
	@Override
	public Location saveLocation(Location location) {
		return repository.save(location);
	}

	@Override
	public Location updateLocation(Location location) {
	
		return repository.save(location);
	}

	@Override
	public void deleteLocation(Location location) {
		repository.delete(location);

	}

	@Override
	public Location getLocationById(int id) {
		
		return repository.findById(id).orElseThrow(null);
	}

	@Override
	public List<Location> getAllLocation() {
		
		return repository.findAll();
	}

	public LocationRepository getRepository() {
		return repository;
	}

	public void setRepository(LocationRepository repository) {
		this.repository = repository;
	}

}
