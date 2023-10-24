package at.technikum.webengbackend.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @NotBlank(message = "street is mandatory")
    private String street;
    @NotBlank(message = "zipcode is mandatory")
    private String zipcode;
    @NotBlank(message = "city is mandatory")
    private String city;
    @NotBlank(message = "country is mandatory")
    private String country;
}
