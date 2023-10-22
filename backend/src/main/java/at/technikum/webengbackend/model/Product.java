package at.technikum.webengbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    private float price;
    private Integer amount;
    private Date upload_date;
    // maybe a date for change?

    public Product(String name, float price, Integer amount) {
        this.name = name;
        this.price = price;
        this.upload_date = new Date();
        this.amount = amount;
    }

}
