package at.technikum.webengbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderDetail> orderDetails;
}
