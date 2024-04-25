package comp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comp.model.Volunteer;

import comp.repository.VolunteerRepository;

@RestController
@RequestMapping("/api/volunteer")
public class VolunteerController {
	@Autowired
	VolunteerRepository volunteerRepository;
	
	@PostMapping("/add")
	public ResponseEntity<Volunteer> addCampDetail(@RequestBody Volunteer volunteer){
		Volunteer volunteerDetails = volunteerRepository.save(volunteer);
		
		return new ResponseEntity<Volunteer>(volunteerDetails,HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Volunteer>> getCampDetail(){
		List<Volunteer> volunteerDetails = volunteerRepository.findAll();
		
		return new ResponseEntity<List<Volunteer>>(volunteerDetails,HttpStatus.OK);
	}
}
