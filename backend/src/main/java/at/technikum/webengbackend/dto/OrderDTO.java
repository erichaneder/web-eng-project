package at.technikum.webengbackend.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    @Getter
    private Long userId;
    @Getter
    private List<ProductDTO> products;
}
