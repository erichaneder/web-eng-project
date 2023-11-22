package at.technikum.webengbackend.repository;

import at.technikum.webengbackend.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<CustomerOrder,Long> {
    Optional<CustomerOrder> findByOrderNo(String orderNo);
}
