package aa.userservice.com.advice;

import aa.userservice.com.exception.UserAlreadyExistsException;
import aa.userservice.com.exception.UserServiceException;
import aa.userservice.com.model.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUsernameNotFoundException() {
        Map<String, String> errors = new HashMap<>();
        errors.put("username", "Username not found in the system.");
        return new ResponseEntity<>(
                ErrorResponseDTO.builder()
                        .message("Username Not Found!")
                        .status(HttpStatus.NOT_FOUND).errors(errors)
                        .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserAlreadyExistsException() {
        Map<String, String> errors = new HashMap<>();
        errors.put("user", "User already exists in the system.");
        return new ResponseEntity<>(ErrorResponseDTO.builder().errors(errors).message("User already exist").status(HttpStatus.FORBIDDEN).build(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserServiceException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserServiceException() {
        return new ResponseEntity<>(ErrorResponseDTO.builder().message("An error occurred in User Service").status(HttpStatus.INTERNAL_SERVER_ERROR).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(
                ErrorResponseDTO.builder()
                        .message("An error occurred in bank account service")
                        .status(HttpStatus.BAD_REQUEST)
                        .errors(errors)
                        .build(), HttpStatus.BAD_REQUEST);
    }

}

