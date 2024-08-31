package aa.userservice.com.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindUserRequest {
    @NotBlank(message = "username cant be empty")
    @Size(min = 4, max = 20,message = "username should be between 4 and 20 characters")
    @Schema(example = "username")
    private String username;
}

