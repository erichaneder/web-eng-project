package at.technikum.webengbackend.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class Address {
    @NotBlank(message = "street is mandatory")
    private String street;
    @NotBlank(message = "zipcode is mandatory")
    private String zipcode;
    @NotBlank(message = "city is mandatory")
    private String city;
    @NotBlank(message = "country is mandatory")
    private String country;

    public Address(String street, String zipcode, String city, String country) {
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
    }
}
