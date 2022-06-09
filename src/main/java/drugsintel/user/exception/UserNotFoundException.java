package drugsintel.user.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 6826436654358918772L;


}