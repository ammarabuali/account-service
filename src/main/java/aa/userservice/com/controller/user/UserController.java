package aa.userservice.com.controller.user;

import aa.userservice.com.annotation.CommonResponse;
import aa.userservice.com.model.dto.UserDto;
import aa.userservice.com.model.entity.UserEntity;
import aa.userservice.com.model.reponse.CreateUserResponse;
import aa.userservice.com.model.reponse.DeleteResponse;
import aa.userservice.com.model.request.FindUserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserController {

    @GetMapping("/find/all")
    @CommonResponse
    ResponseEntity<List<UserDto>> getAllUsers();

    @GetMapping("/find")
    @CommonResponse
    ResponseEntity<UserDto> getUserByUsername(@ModelAttribute FindUserRequest request);

    @PostMapping("/create")
    @CommonResponse
    ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid UserEntity user);

    @DeleteMapping("/delete")
    @CommonResponse
    ResponseEntity<DeleteResponse> deleteUser(@RequestParam String username);

    @DeleteMapping("/delete/all")
    @CommonResponse
    ResponseEntity<DeleteResponse> deleteAllUsers();
}
