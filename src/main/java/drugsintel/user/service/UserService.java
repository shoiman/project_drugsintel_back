package drugsintel.user.service;

import java.util.stream.Stream;

import drugsintel.user.dto.RegUserDto;
import drugsintel.user.dto.UpdateUserDto;
import drugsintel.user.dto.UserDto;

public interface UserService {
	
	//use for registration
	void addUser(RegUserDto regUserDto);
	
	//use in login method
	UserDto getUser(String username);	
	
	//use to do active=false
	UserDto deleteUser(String username);
	
	//use to change login, email
	UserDto updateUser(String username, UpdateUserDto updateUserDto);
	
	
	UserDto changeRole(String username, String role, int period);
	
	void changePassword(String username, String password);
	
	
	////////////////////////
	Stream<UserDto> findByCreationDate(String token);
	
	Stream<UserDto> findByRole(String token);

}
