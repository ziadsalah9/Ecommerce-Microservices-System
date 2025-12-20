package com.MicroserviceEC.ecomerce.order;

import com.MicroserviceEC.ecomerce.order.Enums.PaymentMethod;
import com.MicroserviceEC.ecomerce.orderLine.OrderLine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customer_order")
public class Order {


    @Id
    @GeneratedValue
    private Integer id ;
    private String reference;
    private BigDecimal totalAmount ;

    @CreatedDate
    @Column(updatable = false,nullable = false)
    private LocalDateTime createdDate ;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime LastModifiedDate ;

    @Enumerated(EnumType.STRING)
     private PaymentMethod paymentMethod;

    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;
     private String customerId;


}
