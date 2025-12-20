package com.MicroserviceEC.ecomerce.customer;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustmoerRepository extends MongoRepository<Customer, String> {

}
