package comp.service;

import comp.exception.UserException;
import comp.model.User;

public interface UserService {

	public User findById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;
}
