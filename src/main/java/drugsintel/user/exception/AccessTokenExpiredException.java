package drugsintel.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
public class AccessTokenExpiredException extends RuntimeException {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	public AccessTokenExpiredException() {
		super("Access token is expired");

	}


}
