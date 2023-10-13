package at.technikum.webengbackend.model;

import at.technikum.webengbackend.util.Role;
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
    private String address;
    private String zipcode;
    private String city;
    private String country;

    public User(String name, String password, String mail, Role role, String phonenumber, String address, String zipcode, String city, String country) {
        this.name = name;
        this.password = password;
        this.mail = mail;
        this.role = role;
        this.phonenumber = phonenumber;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
    }

}
