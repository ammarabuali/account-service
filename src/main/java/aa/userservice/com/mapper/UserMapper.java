package aa.userservice.com.mapper;

import aa.userservice.com.model.dto.UserDto;
import aa.userservice.com.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
 
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto toDto(UserEntity userEntity);
}