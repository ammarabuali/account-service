package aa.userservice.com.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor

public class UserServiceException extends RuntimeException {
    public UserServiceException(String message) {
        super(message);
    }
}
