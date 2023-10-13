package at.technikum.webengbackend.model;

import at.technikum.webengbackend.util.Role;
import at.technikum.webengbackend.model.Address;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;
    private String mail;
    private Role role;
    private String phonenumber;
    @Embedded
    private Address address;

    public User(String name, String password, String mail, Role role, String phonenumber, Address address) {
        this.name = name;
        this.password = password;
        this.mail = mail;
        this.role = role;
        this.phonenumber = phonenumber;
        this.address = address;
    }

}
