package drugsintel.user.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
  schema -> floor plan

database -> house

table -> room
 */


@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Builder

//@Table(schema= "sign",name = "user")
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	Long id;
	@Column(name = "username")
	String username;
	
	@Column(name = "email")
	String email;	
	
	@Column(name = "password")
	String password;
	
	@Column(name = "active")
	Boolean active = true;
	
	
	public User(String username, String email, String password) {
		this.username = username.toLowerCase();
		this.email = email.toLowerCase();
		this.password = password;
	}
	

}
