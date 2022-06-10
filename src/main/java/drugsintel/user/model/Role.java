package drugsintel.user.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
@NoArgsConstructor
@Entity
@NotNull
//@Table(schema= "sign",name = "role")
public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1190727408177054149L;
	
	@Id
    @Column(name = "id")
	Long id;
	@Column(name = "name")
	String name;

	@ManyToMany
	@JoinTable(
			//schema= "sign",
	//name = "role_route",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "route_id"))
	Set <Route> routes;

	
	
}
