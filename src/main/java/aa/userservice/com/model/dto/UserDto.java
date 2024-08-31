package aa.userservice.com.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}