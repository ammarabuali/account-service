package aa.userservice.com.repo;

import aa.userservice.com.model.entity.UserEntity;
import aa.userservice.com.model.reponse.DeleteResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UserRepo extends MongoRepository<UserEntity, String> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByPhoneNumber(String phoneNumber);

    boolean existsByUsername(String username);

    void deleteByUsername(String username);

}