package at.technikum.webengbackend.dto;

import at.technikum.webengbackend.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse {
    String token;
    Long userid;
    Role role;
}
