package comp.controller;

import comp.model.DonateMedicines;
import comp.model.User;
import comp.repository.DonateMedicineRepository;
import comp.repository.UserRepository;
import comp.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdminUserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DonateMedicineRepository donateMedicineRepository;

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


    @GetMapping("/user/donations")
    public ResponseEntity<List<DonateMedicines>> getDonatedMedicines(){
        List<DonateMedicines> medicines = donateMedicineRepository.findAll();
        return new ResponseEntity<>(medicines,HttpStatus.ACCEPTED);
    }
}
