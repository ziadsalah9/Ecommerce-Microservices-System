package com.MicroserviceEC.ecomerce.orderLine;


import com.MicroserviceEC.ecomerce.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class OrderLine {


    @Id
    @GeneratedValue
    private Integer Id ;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer productid;

    private double quantity;
}
