package at.technikum.webengbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotNull(message = "Price is mandatory")
    private float price;

    @NotNull(message = "Image is mandatory")
    private String image;

    private String description;

    @NotNull(message = "Amount is mandatory")
    private Integer amount;

    @NotNull(message = "Upload date is mandatory")
    @Temporal(TemporalType.TIMESTAMP)
    private Date upload_date;
    // maybe a date for change?
}
