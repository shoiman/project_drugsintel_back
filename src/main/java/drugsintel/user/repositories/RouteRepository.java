package drugsintel.user.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import drugsintel.user.model.Role;

public interface RouteRepository extends JpaRepository<Role, Long> {


}
