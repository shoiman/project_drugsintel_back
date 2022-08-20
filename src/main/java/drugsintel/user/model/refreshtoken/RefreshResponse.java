package drugsintel.user.model.refreshtoken;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshResponse {
		  private String accessToken;
		  private String refreshToken;
		  private String tokenType = "Bearer";
		  
		  public RefreshResponse(String accessToken, String refreshToken) {
		    this.accessToken = accessToken;
		    this.refreshToken = refreshToken;
	}
}
