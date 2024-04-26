package comp.controller;
import comp.config.JwtProvider;
import comp.model.Address;
import comp.repository.AddressRepository;
import comp.repository.UserRepository;
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
		
		@GetMapping("/profile")
		public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt)throws UserException{
			User user = userService.findUserProfileByJwt(jwt);
			return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
		}

		@PostMapping("/addAddress")
		public ResponseEntity<ApiResponse> addAddress(@RequestHeader("Authorization")String jwt, @RequestBody Address address) throws UserException {
			String  email = jwtProvider.getEmailFromToken(jwt);
			User user = userRepository.findByEmail(email);
			if (user.equals(null)){
				throw new UserException("User not Found");
			}else {
				addressRepository.save(address);
				ApiResponse res = new ApiResponse();
				res.setMessage("Address Added Successfully");
				res.setStatus(true);
				return new ResponseEntity<ApiResponse>(res,HttpStatus.CREATED);
			}

		}
}
