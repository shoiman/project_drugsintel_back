package drugsintel.user.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import drugsintel.user.model.id.UserRoleId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NotNull
//@Table(schema= "sign",name = "user_role")
@IdClass(UserRoleId.class)
public class UserRole implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4717118379059765359L;
	@Id
	@Column(name = "user_id")
	Long userId;
	@Id
	@Column(name = "role_id")
	Long roleId;
	@Id
	@Column(name = "role_start")
	LocalDateTime roleStart = LocalDateTime.now();
	@Id
	@Column(name = "role_end")
	LocalDateTime roleEnd = LocalDateTime.now().plusYears(100);
	//LocalDateTime roleEnd;
	
	
	public UserRole(Long userId, Long roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}
	
	

}
