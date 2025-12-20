package com.MicroserviceEC.ecomerce.order.products;


import com.MicroserviceEC.ecomerce.order.Exceptions.BussinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {


    @Value("${application.config.product-url}")
    private  String url ;

    private final RestTemplate restTemplate;

    public List<PurchaseResponse> purchaseProducts (List<PurchaseRequest> requestBody){


        var headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseRequest>>  request = new HttpEntity<>(requestBody, headers);
        ParameterizedTypeReference<List<PurchaseResponse>> responseType =
                new ParameterizedTypeReference<>(){};


        ResponseEntity<List<PurchaseResponse>> responseEntity =
             //   restTemplate.exchange(url+"/products",  HttpMethod.POST, request, responseType);
        restTemplate.exchange(url+"/purchase",  HttpMethod.POST, request, responseType);

        if(responseEntity.getStatusCode().isError()){

            throw new BussinessException("an error occured while processing the product purchase "
                    +responseEntity.getStatusCode());
        }

        return responseEntity.getBody();

    }

}
