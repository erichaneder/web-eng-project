package at.technikum.webengbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 5, max = 50, message = "Name must be between 5 and 50 characters")
    private String name;

    @Size(max = 30, message = "Salutation must be max 30 characters")
    private String salutation;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    @NotBlank(message = "Password is mandatory")
    /*@Pattern.List({
            @Pattern(regexp = "(?=.*[0-9])", message = "Password must contain at least one digit"),
            @Pattern(regexp = "(?=.*[a-z])", message = "Password must contain at least one lowercase letter"),
            @Pattern(regexp = "(?=.*[A-Z])", message = "Password must contain at least one uppercase letter")
    })*/
    private String password;

    @Column(unique = true)
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email must be valid")
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;


    private String phonenumber;

    @Embedded
    private Address address;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
