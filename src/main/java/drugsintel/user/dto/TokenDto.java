package drugsintel.user.dto;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NonNull
public class TokenDto {
		
	private String token;
	private String type = "Bearer";
	
	//new field
	private String refreshToken;
	
	private Long id;
	private String role;
	private List<String> routes;
	
	public TokenDto(String token, Long id, String role, List<String> routes) {
		this.token = token;
		this.id = id;
		this.role = role;
		this.routes = routes;
	}
	
	public TokenDto(String token, String refreshToken, Long id, String role, List<String> routes) {
		this.token = token;
		this.refreshToken = refreshToken;
		this.id = id;
		this.role = role;
		this.routes = routes;
	}
	
	

}
