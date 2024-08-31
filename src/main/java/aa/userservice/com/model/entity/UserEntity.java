package aa.userservice.com.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ACCOUNTS")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private String id;

    @NotBlank(message = "username can't be left empty")
    @Size(min = 4, max = 20)
    @Schema(example = "String")
    private String username;

    @NotBlank(message = "first name can't be left empty")
    @Size(min = 4, max = 20)
    @Schema(example = "String")
    private String firstName;

    @NotBlank(message = "last name can't be left empty")
    @Size(min = 4, max = 20)
    @Schema(example = "String")
    private String lastName;

    @NotBlank(message = "Phone number can't be left empty")
    @Pattern(regexp = "^\\+?[0-9 ()-]{7,15}$", message = "Invalid phone number")
    @Schema(example = "+1234567890")
    private String phoneNumber;

    @Email(message = "Please enter a valid email")
    @NotBlank(message = "Email can't be left empty")
    @Schema(example = "john.doe@example.com")
    private String email;

    @NotBlank(message = "Password can't be left empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, and one special character")
    @Schema(example = "Password123!")
    private String password;

    @JsonIgnore
    private String role;
    @JsonIgnore
    private List<String> permissions;

}