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

import comp.model.Partner;

import comp.repository.PartnerRepository;


@RestController
@RequestMapping("/api/partner")
public class PartnerController {
	@Autowired
	PartnerRepository partnerRepository;
	
	@PostMapping("/add")
	public ResponseEntity<Partner> addCampDetail(@RequestBody Partner partner){
		Partner partnerDetails = partnerRepository.save(partner);
		
		return new ResponseEntity<Partner>(partnerDetails,HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Partner>> getCampDetail(){
		List<Partner> partnerDetails = partnerRepository.findAll();
		
		return new ResponseEntity<List<Partner>>(partnerDetails,HttpStatus.OK);
	}
}
