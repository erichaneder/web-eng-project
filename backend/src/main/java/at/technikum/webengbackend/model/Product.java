package at.technikum.webengbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
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

    @NotNull(message = "Amount is mandatory")
    private Integer amount;

    @NotNull(message = "Upload date is mandatory")
    @Temporal(TemporalType.TIMESTAMP)
    private Date upload_date;
    // maybe a date for change?

    public Product(String name, float price, Integer amount) {
        this.name = name;
        this.price = price;
        this.upload_date = new Date();
        this.amount = amount;
    }

}
