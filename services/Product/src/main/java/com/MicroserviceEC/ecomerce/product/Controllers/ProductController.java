package com.MicroserviceEC.ecomerce.product.Controllers;

import com.MicroserviceEC.ecomerce.product.Dtos.ProductRequest;
import com.MicroserviceEC.ecomerce.product.Dtos.productPurchaseRequest;
import com.MicroserviceEC.ecomerce.product.Dtos.productPurchaseResponse;
import com.MicroserviceEC.ecomerce.product.Dtos.productResponse;
import com.MicroserviceEC.ecomerce.product.Product;
import com.MicroserviceEC.ecomerce.product.Services.productService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final productService _service;


    @PostMapping
    public ResponseEntity<Integer> addProduct(@RequestBody @Valid ProductRequest request) {


        return ResponseEntity.ok(_service.createProduct(request));

    }

    @PostMapping("/purchase")
    public ResponseEntity<List<productPurchaseResponse>> PurchaseProduct(@RequestBody List<productPurchaseRequest> request){

        return ResponseEntity.ok(_service.purchaseProducts(request));

    }


    @GetMapping("/{id}")
    public ResponseEntity<productResponse> getProductById(@PathVariable("id") int id){

        return ResponseEntity.ok(_service.findProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<productResponse>> getAllProducts(){

        return ResponseEntity.ok(_service.findAllProducts());
    }

}

