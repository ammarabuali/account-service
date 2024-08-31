package aa.userservice.com.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO {
    @Schema(example = "request http status")
    private HttpStatus status;
    @Schema(example = "error message")
    private String message;
    @Schema(example = "errors list")
    private Map<String, String> errors;
}