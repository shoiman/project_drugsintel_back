package drugsintel.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import drugsintel.user.model.UserRole;
import drugsintel.user.model.id.UserRoleId;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
	
	
    Optional<UserRole> findFirstByUserIdAndRoleStartBeforeAndRoleEndAfterOrderByRoleStartDesc(Long id, LocalDateTime currentTime1, LocalDateTime currentTime2);

    Stream <UserRole> getByUserIdAndRoleStartBeforeAndRoleEndAfter(Long id, LocalDateTime currentTime1, LocalDateTime currentTime2);
    

}
