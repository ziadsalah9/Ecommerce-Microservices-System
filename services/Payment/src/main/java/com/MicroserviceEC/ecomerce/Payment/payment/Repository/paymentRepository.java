package com.MicroserviceEC.ecomerce.Payment.payment.Repository;

import com.MicroserviceEC.ecomerce.Payment.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface paymentRepository extends JpaRepository<Payment,Integer> {


}
