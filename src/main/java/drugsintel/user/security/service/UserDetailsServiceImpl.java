package drugsintel.user.security.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import drugsintel.user.model.User;
import drugsintel.user.repositories.RoleRepository;
import drugsintel.user.repositories.RouteRepository;
import drugsintel.user.repositories.UserRepository;
import drugsintel.user.repositories.UserRoleRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	UserRoleRepository userRoleRepository;
	RoleRepository roleRepository;
	RouteRepository routeRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = new User();
		if (username.contains("@")) {
			user = userRepository.findByEmail(username.toLowerCase())
					.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		} else {
			user = userRepository.findByUsername(username.toLowerCase())
					.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		}
		return UserDetailsImpl.build(user);
	}

}
