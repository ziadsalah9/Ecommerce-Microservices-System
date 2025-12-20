package com.MicroserviceEC.ecomerce.product.Mapping;

import com.MicroserviceEC.ecomerce.product.Category;
import com.MicroserviceEC.ecomerce.product.Dtos.ProductRequest;
import com.MicroserviceEC.ecomerce.product.Dtos.productPurchaseResponse;
import com.MicroserviceEC.ecomerce.product.Dtos.productResponse;
import com.MicroserviceEC.ecomerce.product.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class productMapper {
    public Product toProduct(ProductRequest request) {

        return Product.builder()
                .Id(request.Id())
                .Name(request.Name())
                .Description(request.Description())
                .Price(request.Price())
                .availableQuantity(request.availableQuantity())
                .category(Category.builder()
                        .Id(request.category_id()).build())
                .build();

    }

    public productResponse toProductRespone( Product product) {

       return new productResponse(

               product.getId(),
               product.getName(),
               product.getDescription(),
               product.getAvailableQuantity(),
               product.getPrice(),
               product.getCategory().getId(),
               product.getCategory().getName(),
               product.getCategory().getDescription()

       );
    }

    public productPurchaseResponse toProductPurchasedRespone(Product product, @NotNull(message = "quantity is required") double quantity) {

        return new productPurchaseResponse(


                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );

    }
}
