package at.technikum.webengbackend.dto;

import at.technikum.webengbackend.model.Address;
import at.technikum.webengbackend.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String name;
    private String salutation;
    private String password;
    private String email;
    private Role role;
    private String phonenumber;
    private Address address;
}
