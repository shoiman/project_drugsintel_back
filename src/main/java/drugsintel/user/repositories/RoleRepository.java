package drugsintel.user.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import drugsintel.user.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Optional<Role> findByName (String roleName);
	
	Optional<Role> findById(long id);
	
	

}
