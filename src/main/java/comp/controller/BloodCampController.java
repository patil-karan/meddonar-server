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

import comp.model.BloodCamp;
import comp.repository.BloodCampRepository;

@RestController
@RequestMapping("/api/camp")
public class BloodCampController {
	
	@Autowired
	BloodCampRepository bloodCampRepository;
	
	@PostMapping("/add")
	public ResponseEntity<BloodCamp> addCampDetail(@RequestBody BloodCamp bloodCamp){
		BloodCamp bloodCampDetails = bloodCampRepository.save(bloodCamp);
		
		return new ResponseEntity<BloodCamp>(bloodCampDetails,HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<BloodCamp>> getCampDetail(){
		List<BloodCamp> bloodCampDetails = bloodCampRepository.findAll();
		
		return new ResponseEntity<List<BloodCamp>>(bloodCampDetails,HttpStatus.OK);
	}
}
