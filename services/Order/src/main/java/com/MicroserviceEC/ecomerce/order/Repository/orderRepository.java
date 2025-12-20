package com.MicroserviceEC.ecomerce.order.Repository;


import com.MicroserviceEC.ecomerce.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface orderRepository extends JpaRepository<Order, Integer> {


}
