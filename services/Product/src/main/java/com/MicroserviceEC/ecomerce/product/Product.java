package com.MicroserviceEC.ecomerce.product;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Integer Id;
    private String Name;
    private String Description;
    private double availableQuantity;
    private BigDecimal Price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private  Category category;

}
