package aa.userservice.com.controller.user;

import aa.userservice.com.model.dto.UserDto;
import aa.userservice.com.model.entity.UserEntity;
import aa.userservice.com.model.reponse.CreateUserResponse;
import aa.userservice.com.model.reponse.DeleteResponse;
import aa.userservice.com.model.request.FindUserRequest;
import aa.userservice.com.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @Override
    public ResponseEntity<UserDto> getUserByUsername(FindUserRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByUsername(request.getUsername()));
    }

    @Override
    public ResponseEntity<CreateUserResponse> createUser(UserEntity user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @Override
    public ResponseEntity<DeleteResponse> deleteUser(String username) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteByUsername(username));
    }

    @Override
    public ResponseEntity<DeleteResponse> deleteAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteAll());
    }
}
