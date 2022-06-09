package drugsintel.user.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import drugsintel.user.model.RoleRoute;
import drugsintel.user.model.id.RoleRouteId;

public interface RoleRouteRepository extends JpaRepository<RoleRoute, RoleRouteId> {


}
