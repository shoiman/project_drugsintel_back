package drugsintel.user.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(code = HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 6826436654358918772L;
    
    public UserAlreadyExistsException(String login) {
		super("User " + login +" exists");
	}


}
