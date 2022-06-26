package drugsintel.user.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import drugsintel.user.dto.EmailDto;
import drugsintel.user.dto.RegUserDto;
import drugsintel.user.dto.TokenDto;
import drugsintel.user.dto.UpdateUserDto;
import drugsintel.user.dto.UserDto;
import drugsintel.user.model.Role;
import drugsintel.user.model.Route;
import drugsintel.user.repositories.RoleRepository;
import drugsintel.user.security.jwt.JwtUtils;
import drugsintel.user.security.service.UserDetailsImpl;
import drugsintel.user.service.UserService;

@CrossOrigin(origins="*", maxAge = 3600, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT },
 		allowedHeaders = "*")
@RestController
@RequestMapping("/accounting")
public class AuthController {

	AuthenticationManager authenticationManager;
	RoleRepository roleRepository;
	UserService userService;
	PasswordEncoder encoder;
	JwtUtils jwtUtils;

	@Autowired
	public AuthController(UserService userService, RoleRepository roleRepository, PasswordEncoder encoder,
			JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
		this.userService = userService;
		this.roleRepository = roleRepository;
		this.encoder = encoder;
		this.jwtUtils = jwtUtils;
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/login")
	public TokenDto authenticateUser(@Validated @RequestBody EmailDto emailDto) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(emailDto.getUsername().toLowerCase(), emailDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		String jwt = jwtUtils.generateJwtToken(userDetails);

		String role = userService.getUser(userDetails.getUsername().toLowerCase()).getRole();

		// roles
		Role userRole = roleRepository.findByName(role).get();
		// user id
		Long id_user = userDetails.getId();
		// routes
		Set<Route> temp = userRole.getRoutes();
		List<String> routes = temp.stream().map(l -> l.getRouteName()).collect(Collectors.toList());

		return new TokenDto(jwt, id_user, role, routes);
	}

	@PostMapping("/registration")
	public void registerUser(@RequestBody RegUserDto regUserDto) {
		System.out.println("controller regist");
		userService.addUser(regUserDto);
	}

	@GetMapping("/username/{username}")
	public UserDto getUser(@PathVariable String username) {
		System.out.println("1: i was here");
		return userService.getUser(username);
	}

	@DeleteMapping("/delete/{username}")
	public UserDto deleteUser(@PathVariable String username) {
		return userService.deleteUser(username);
	}

	@PutMapping("/update*")
	public UserDto updateUser(@RequestHeader("Authorization") String token, @RequestBody UpdateUserDto updateUserDto) {
		String temp = token.substring(7, token.length());
		String username = jwtUtils.getUserNameFromJwtToken(temp);
		return userService.updateUser(username, updateUserDto);
	}

	@PutMapping("/user/{username}/role/{role}/period/{period}")
	public UserDto changeRole(@PathVariable String username, @PathVariable String role, @PathVariable int period) {
		return userService.changeRole(username, role, period);
	}

	@PutMapping("/password")
	public void changePassword(@RequestHeader("Authorization") String token,
			@RequestHeader("X-Password") String password) {
		String temp = token.substring(7, token.length());
		String username = jwtUtils.getUserNameFromJwtToken(temp);
		userService.changePassword(username, password);
	}

}