package drugsintel.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ResponseStatus(code = HttpStatus.LOCKED)
public class UserNotActiveException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2216514623922389198L;
	

    public UserNotActiveException(String user) {
    	super("User " + user + " is not active");
    }

}
