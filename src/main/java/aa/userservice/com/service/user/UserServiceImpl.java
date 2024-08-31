package aa.userservice.com.service.user;

import aa.userservice.com.constant.UserRole;
import aa.userservice.com.exception.UserAlreadyExistsException;
import aa.userservice.com.exception.UserServiceException;
import aa.userservice.com.mapper.UserMapper;
import aa.userservice.com.model.dto.UserDto;
import aa.userservice.com.model.entity.UserEntity;
import aa.userservice.com.model.reponse.CreateUserResponse;
import aa.userservice.com.model.reponse.DeleteResponse;
import aa.userservice.com.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<UserDto> findAll() {
        try {
            log.info("Finding all users");
            List<UserEntity> users = userRepository.findAll();
            return users.stream().map(UserMapper.INSTANCE::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error finding all users : {}", e.getMessage());
            throw new UserServiceException("An error occurred while finding all users");
        }
    }

    @Override
    public DeleteResponse deleteAll() {
        try {
            log.info("Deleting all users");
            userRepository.deleteAll();
            return DeleteResponse.builder().deleted(true).build();
        } catch (Exception e) {
            log.error("An error occurred while deleting all users");
            throw new UserServiceException("An error occurred while deleting all users");
        }
    }

    @Override
    public UserDto findByUsername(String username) {
        try {
            UserEntity user = userRepository.findByUsername(username.toLowerCase()).orElseThrow();
            return UserMapper.INSTANCE.toDto(user);
        } catch (NoSuchElementException e) {
            throw new UsernameNotFoundException("user not found " + username + " " + e.getMessage());
        } catch (Exception e) {
            throw new UserServiceException("An error occurred while finding user by username: " + e.getMessage());
        }
    }

    @Override
    public CreateUserResponse createUser(UserEntity user) {
        try {
            log.info("Creating new user {}", user.getUsername());
            validateUserNonExistence(user);
            user.setEmail(user.getEmail().toLowerCase());
            user.setUsername(user.getUsername().toLowerCase());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(UserRole.USER.name());
            user.setPermissions(new ArrayList<>()); //give default permissions to "user" role
            userRepository.save(user);
            return CreateUserResponse.builder().created(true).build();
        } catch (UserAlreadyExistsException e) {
            log.error("user {} already exist", user.getUsername());
            throw new UserAlreadyExistsException("User already exists " + e.getMessage());
        } catch (Exception e) {
            log.error("An error occurred while creating user : {}", user);
            throw new UserServiceException("An error occurred while creating user " + " " + user.getUsername() + " " + e.getMessage());
        }
    }


    @Override
    public DeleteResponse deleteByUsername(String username) {
        try {
            log.info("Deleting user {}", username);
            if (userRepository.findByUsername(username).isPresent()) {
                userRepository.deleteByUsername(username);
                return DeleteResponse.builder().deleted(true).build();
            } else {
                throw new UsernameNotFoundException("User not found: " + username);
            }
        } catch (UsernameNotFoundException e) {
            log.error("something went wrong while deleting user : {}", username);
            throw new UsernameNotFoundException("User not found: " + username);
        } catch (Exception e) {
            log.error("An error occurred while deleting user : {}", username);
            throw new UserServiceException("An error occurred while deleting user " + " " + username + " " + e.getMessage());
        }
    }

    private void validateUserNonExistence(UserEntity user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()
                || userRepository.findByEmail(user.getEmail()).isPresent()
                || userRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent()) {
            log.error("user {} already exists", user);
            throw new UserAlreadyExistsException("User with username " + user.getUsername() + " already exists.");
        }
    }

}

