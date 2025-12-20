package com.MicroserviceEC.ecomerce.product.Repositories;

import com.MicroserviceEC.ecomerce.product.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface productRepository extends JpaRepository<Product,Integer>{
     List<Product>findAllByIdInOrderById(List<@NotNull(message = "product is required") Integer> ids);
}
