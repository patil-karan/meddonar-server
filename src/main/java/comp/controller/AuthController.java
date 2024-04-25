package comp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comp.config.JwtProvider;
import comp.exception.UserException;
import comp.model.User;
import comp.model.Cart;
import comp.repository.UserRepository;
import comp.request.LoginRequest;
import comp.response.AuthResponse;
import comp.service.CartService;
import comp.service.CustomUserServiceImplementation;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private CustomUserServiceImplementation customUserService;
	@Autowired
	private CartService cartService;

	public AuthController(UserRepository userRepository, JwtProvider jwtProvider, PasswordEncoder passwordEncoder,
			CustomUserServiceImplementation customUserService, CartService cartService) {
		this.userRepository = userRepository;
		this.jwtProvider = jwtProvider;
		this.passwordEncoder = passwordEncoder;
		this.customUserService = customUserService;
		this.cartService = cartService;
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello World";
	}

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException {
		String email = user.getEmail();
		String password = user.getPassword();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();

		User isEmailExist = userRepository.findByEmail(email);

		if (isEmailExist != null) {
			throw new UserException("This email is alreadu Exist With Another Account");
		}

		User createUser = new User();
		createUser.setEmail(email);
		createUser.setPassword(passwordEncoder.encode(password));
		createUser.setFirstName(firstName);
		createUser.setLastName(lastName);
		createUser.setRole("USER");

		User savedUser = userRepository.save(createUser);

		Cart cart = cartService.CreateCart(savedUser);

		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
				savedUser.getPassword());

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtProvider.generateToken(authentication);

		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("SignUp Successfull");

		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
	}

	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest) throws UserException {

		String email = loginRequest.getEmail();
		String password = loginRequest.getPassword();

		Authentication authentication = Authentication(email, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtProvider.generateToken(authentication);

		User user = userRepository.findByEmail(email);
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setRole(user.getRole());
		authResponse.setMessage("SignIn Successfull");

		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
	}

	private Authentication Authentication(String email, String password) {
		UserDetails userDetails = customUserService.loadUserByUsername(email);

		if (userDetails == null) {
			throw new BadCredentialsException("Invalid Email");
		}

		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid Password");
		}
		return new UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities());
	}
}
