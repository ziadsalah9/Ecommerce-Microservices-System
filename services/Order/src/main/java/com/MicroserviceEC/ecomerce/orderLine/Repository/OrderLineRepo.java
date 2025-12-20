package com.MicroserviceEC.ecomerce.orderLine.Repository;

import com.MicroserviceEC.ecomerce.orderLine.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepo extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findAllOrderLinesByOrderId(Integer orderId);
}
