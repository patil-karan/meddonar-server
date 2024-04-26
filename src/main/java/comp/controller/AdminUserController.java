package comp.controller;

import comp.model.User;
import comp.repository.UserRepository;
import comp.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdminUserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    public ResponseEntity<User> getAllUsers(){
        List<User> users = userRepository.findAll();
        return new ResponseEntity<User>((User) users, HttpStatus.ACCEPTED);
    }

    @PostMapping("/user/userId")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId){
        userRepository.deleteById(userId);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("User Deleted Successfully");
        apiResponse.setStatus(true);

        return  new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.ACCEPTED);
    }
}
