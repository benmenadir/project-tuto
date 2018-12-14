package com.myappnadir.location.controllers;



import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myappnadir.location.entities.Location;
import com.myappnadir.location.repos.LocationRepository;
import com.myappnadir.location.service.LocationService;
import com.myappnadir.location.util.EmailUtil;
import com.myappnadir.location.util.ReportUtil;

@Controller
public class LocationController {

	@Autowired 
	LocationService service;
	
	@Autowired
	LocationRepository repository;
	
	@Autowired
	ReportUtil reportUtil;
	
	@Autowired
	EmailUtil emailUtil;
	
	@Autowired
	ServletContext sc;
	
	@RequestMapping("/showCreate")
	public String showCreate() {
		return "createLocation";
	}
	@RequestMapping("/saveloc")
	public String saveLocation(@ModelAttribute("location") Location location, ModelMap modelmap) {
		Location locationSaved = service.saveLocation(location);	
		String msg = "Location saved with id : " + locationSaved.getId();
		
		modelmap.addAttribute("msg", msg);
		
		emailUtil.senEmail("springxyzabctest@gmail.com", 
							"location Saved", 
							" Location Saved Successfully and about  to return a response");
		return "createLocation";
	}
	
	@RequestMapping("/displayLocations")
	public String displayLocations(ModelMap modelmap) {
		List<Location> locations=service.getAllLocation();
		modelmap.addAttribute("locations",locations);
		return "displayLocations";
	}
	@RequestMapping("deleteLocation")
	public String deleteLocation(@RequestParam("id") int id, ModelMap modelmap) {
		//Location location=service.getLocationById(id);
		Location location = new Location();
		location.setId(id);
		service.deleteLocation(location);
		
		List<Location> locations=service.getAllLocation();
		modelmap.addAttribute("locations",locations);
		return "displayLocations";
	}
	@RequestMapping("/showUpdate")
	public String showUpdate(@RequestParam("id") int id, ModelMap modelmap) {
		Location location=service.getLocationById(id);
		modelmap.addAttribute("location",location);
		return "updateLocation";
	}
	
	@RequestMapping("/updateLoc")
	public String updateLocation(@ModelAttribute("location") Location location, ModelMap modelmap) {
		service.updateLocation(location);
		
		List<Location> locations=service.getAllLocation();
		modelmap.addAttribute("locations",locations);
		return "displayLocations";
	}
	
	@RequestMapping("/generateReport")
	public String generateReport() {
		
		String path = sc.getRealPath("/");
		List<Object[]> data = repository.findTypeAndTypeCount();
		
		reportUtil.generatePieChart(path, data);
		return "report";
		
	}
	
	
}
