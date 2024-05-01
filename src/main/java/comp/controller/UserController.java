package comp.controller;
import comp.config.JwtProvider;
import comp.model.Address;
import comp.model.DonateMedicines;
import comp.repository.AddressRepository;
import comp.repository.DonateMedicineRepository;
import comp.repository.UserRepository;
import comp.response.AddressResponse;
import comp.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import comp.exception.UserException;
import comp.model.User;
import comp.service.UserService;
	@RestController
	@RequestMapping("/api/users")
	public class UserController {

		@Autowired
		private UserService userService;
		@Autowired
		private AddressRepository addressRepository;
		@Autowired
		private JwtProvider jwtProvider;
		@Autowired
		private UserRepository userRepository;
		@Autowired
		private DonateMedicineRepository donateMedicineRepository;
		
		@GetMapping("/profile")
		public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt)throws UserException{
			User user = userService.findUserProfileByJwt(jwt);
			return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
		}

		@GetMapping("/getAddress/{addressId}")
		public ResponseEntity<Address> getUserAddress(@RequestHeader("Authorization") String jwt,@PathVariable Long addressId)throws UserException{
			Address address = addressRepository.findById(addressId).orElseThrow();
			return new ResponseEntity<Address>(address,HttpStatus.ACCEPTED);
		}

		@PostMapping("/addAddress")
		public ResponseEntity<AddressResponse> addAddress(@RequestHeader("Authorization")String jwt, @RequestBody Address address) throws UserException {
			String  email = jwtProvider.getEmailFromToken(jwt);
			User user = userRepository.findByEmail(email);
			if (user.equals(null)){
				throw new UserException("User not Found");
			}else {

				Address address1 = addressRepository.save(address);
				AddressResponse addressResponse = new AddressResponse();
				addressResponse.setId(address1.getId());

				return new ResponseEntity<AddressResponse>(addressResponse,HttpStatus.CREATED);
			}

		}

		@PostMapping("/donateMedicine")
		public ResponseEntity<ApiResponse> donateMedicine(@RequestBody DonateMedicines donateMedicines){

			donateMedicineRepository.save(donateMedicines);


			ApiResponse res = new ApiResponse();
			res.setMessage("Donated Successfully");
			res.setStatus(true);
			return new ResponseEntity<>(res,HttpStatus.CREATED);
		}
}
