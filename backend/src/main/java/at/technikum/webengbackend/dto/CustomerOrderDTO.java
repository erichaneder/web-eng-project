package at.technikum.webengbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerOrderDTO {
    private Long id;
    private String orderNo;
    private float totalAmount;
    private Instant createdOn;
    private Instant lastUpdatedOn;
    private UserDTO user;
    private List<OrderDetailDTO> orderDetails;
    private int totalQuantity;
}
