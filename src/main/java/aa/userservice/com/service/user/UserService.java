package aa.userservice.com.service.user;

import aa.userservice.com.model.dto.UserDto;
import aa.userservice.com.model.entity.UserEntity;
import aa.userservice.com.model.reponse.CreateUserResponse;
import aa.userservice.com.model.reponse.DeleteResponse;


import java.util.List;

public interface UserService {
    List<UserDto> findAll();

    UserDto findByUsername(String username);

    CreateUserResponse createUser(UserEntity user);

    DeleteResponse deleteByUsername(String username);

    DeleteResponse deleteAll();
}