package drugsintel.user.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import drugsintel.user.model.User;
import drugsintel.user.model.refreshtoken.RefreshToken;



public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByUser(User user);
}
