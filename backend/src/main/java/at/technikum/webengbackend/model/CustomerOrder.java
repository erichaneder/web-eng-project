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
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "orderNo is mandatory")
    private String orderNo;

    @NotNull(message = "Total Amount is mandatory")
    private float totalAmount;

    @NotNull(message = "Upload date is mandatory")
    @Temporal(TemporalType.TIMESTAMP)
    private Date upload_date;
    // maybe a date for change?
}
