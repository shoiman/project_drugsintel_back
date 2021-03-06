package drugsintel.user.model.id;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode( of = {"userId", "roleId", "roleStart", "roleEnd"})
public class UserRoleId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5267975785072433445L;
	Long userId;
	Long roleId;
	LocalDateTime roleStart;
	LocalDateTime roleEnd;
	
}
