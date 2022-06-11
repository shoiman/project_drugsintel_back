package drugsintel.user.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import drugsintel.user.dto.EmailDto;
import drugsintel.user.dto.RegUserDto;
import drugsintel.user.dto.UpdateUserDto;
import drugsintel.user.dto.UserDto;
import drugsintel.user.exception.RoleNotFoundException;
import drugsintel.user.exception.UserAlreadyExistsException;
import drugsintel.user.exception.UserNotActiveException;
import drugsintel.user.exception.UserNotFoundException;
import drugsintel.user.model.Role;
import drugsintel.user.model.User;
import drugsintel.user.model.UserRole;
import drugsintel.user.repositories.RoleRepository;
import drugsintel.user.repositories.RoleRouteRepository;
import drugsintel.user.repositories.RouteRepository;
import drugsintel.user.repositories.UserRepository;
import drugsintel.user.repositories.UserRoleRepository;

@Service
public class UserServiceImpl implements UserService {

	ModelMapper modelMapper;
	UserRepository userRepository;
	RoleRepository roleRepository;
	RouteRepository routeRepository;
	RoleRouteRepository roleRouteRepository;
	UserRoleRepository userRoleRepository;
	PasswordEncoder passwordEncoder;
	String token;

	

	@Autowired
	public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, RoleRepository roleRepository,
			RouteRepository routeRepository, RoleRouteRepository roleRouteRepository,
			UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
		this.modelMapper = modelMapper;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.routeRepository = routeRepository;
		this.roleRouteRepository = roleRouteRepository;
		this.userRoleRepository = userRoleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional
	public void addUser(RegUserDto regUserDto) {
		User user = userRepository.findByEmail(regUserDto.getEmail()).orElse(null);
		if (user != null) {
			throw new UserAlreadyExistsException(user.getUsername());
		}
		user = modelMapper.map(regUserDto, User.class);
		
		user.setUsername(regUserDto.getUsername().toLowerCase());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		Role role = roleRepository.findByName("user".toUpperCase()).orElseThrow(() -> new RoleNotFoundException());
		
		UserRole userRole = new UserRole(user.getId(), role.getId());
		userRoleRepository.save(userRole);
	}

	
	
	///GET USER
	@Override
	@Transactional
	public UserDto getUser(String username) {		
		User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException());
		if (!user.getActive()) {
			throw new UserNotActiveException();
		}
		System.out.println(user.getUsername());
		//check role exist
		UserRole userRole = userRoleRepository.findFirstByUserIdAndRoleStartBeforeAndRoleEndAfterOrderByRoleStartDesc(user.getId(), LocalDateTime.now(), LocalDateTime.now())
				.orElseThrow(UserNotFoundException::new);
			
		Role role = roleRepository.getById(userRole.getRoleId());
		
		UserDto userDto = modelMapper.map(user, UserDto.class);
		userDto.setRole(role.getName());
		userDto.setExpireDate(userRole.getRoleEnd().toLocalDate());
		return userDto;
	}


	//DELETE
	@Override
	@Transactional
	public UserDto deleteUser(String username) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException());
		if (!user.getActive()) {
			throw new UserNotActiveException();
		}
		System.out.println(username);
		System.out.println(user.getUsername());
		user.setActive(false);
		UserRole userRole = userRoleRepository
				.findFirstByUserIdAndRoleStartBeforeAndRoleEndAfterOrderByRoleStartDesc(user.getId(), LocalDateTime.now(), LocalDateTime.now())
				.orElseThrow(UserNotFoundException::new);
		System.out.println(userRole);
		Role role = roleRepository.getById(userRole.getRoleId());
		System.out.println(role);
		UserDto userDto = modelMapper.map(user, UserDto.class);
		userDto.setExpireDate(LocalDate.now());
		userDto.setRole(role.getName());
		return userDto;
	}

	
	//UPDATE
	@Override
	@Transactional
	public UserDto updateUser(String username, UpdateUserDto updateUserDto) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException());
		if (!user.getActive()) {
			throw new UserNotActiveException();
		}
		user.setUsername(updateUserDto.getUsername());
		user.setEmail(updateUserDto.getEmail());

		UserRole userRole = userRoleRepository
				.findFirstByUserIdAndRoleStartBeforeAndRoleEndAfterOrderByRoleStartDesc(user.getId(), LocalDateTime.now(), LocalDateTime.now())
				.orElseThrow(UserNotFoundException::new);
		Role role = roleRepository.getById(userRole.getRoleId());
		UserDto getUpdateUser = modelMapper.map(user, UserDto.class);
		getUpdateUser.setRole(role.getName());
		getUpdateUser.setExpireDate(userRole.getRoleEnd().toLocalDate());
		return getUpdateUser;
	}
	

	
	//CHANGE ROLE
	@Override
	@Transactional
	public UserDto changeRole(String username, String role, int period) {		
		User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException());
		if (!user.getActive()) {
			throw new UserNotActiveException();
		}

		LocalDateTime newEndOfRole = LocalDateTime.now().plusDays(period);
		Role role_new = roleRepository.findByName(role.toUpperCase()).orElseThrow(() -> new RoleNotFoundException());		
		UserRole user_role = new UserRole(user.getId(), 
										  role_new.getId(), 
										  LocalDateTime.now(), 
										  newEndOfRole);
		userRoleRepository.save(user_role);		
		
		UserDto getUpdateUser = modelMapper.map(user, UserDto.class);
		getUpdateUser.setRole(role);
		getUpdateUser.setExpireDate(newEndOfRole.toLocalDate());
		return getUpdateUser;
	}

	
	///////////////////////////////////////////////////
	
	@Override
	@Transactional
	public void changePassword(String username, String password) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException());
		password = passwordEncoder.encode(password);
		user.setPassword(password);

	}

	@Override
	public Stream<UserDto> findByCreationDate(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<UserDto> findByRole(String role) {
		// TODO Auto-generated method stub
		return null;
	}


}
