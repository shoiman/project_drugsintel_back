package drugsintel.user.model.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
//Такую аннотацию дают, когда связь с классом существует,
//и в структуре классов следует показать это,
//но таблицы для одного из классов нет, а значит, 
//связи @One-To-Many или @One-To-One использовать не получится.
public class RoleRouteId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4691865374074010425L;
	
	@Column(name = "route_id")
	Long routeId;
	@Column(name = "role_id")
	Long roleId;


}
