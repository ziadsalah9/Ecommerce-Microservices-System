package com.MicroserviceEC.ecomerce.order.Services;

import com.MicroserviceEC.ecomerce.order.Dtos.OrderResponse;
import com.MicroserviceEC.ecomerce.order.Dtos.orderRequest;
import com.MicroserviceEC.ecomerce.order.Exceptions.BussinessException;
import com.MicroserviceEC.ecomerce.order.Mapping.OrderMapper;
import com.MicroserviceEC.ecomerce.order.Repository.orderRepository;
import com.MicroserviceEC.ecomerce.order.customer.CustomerClient;
import com.MicroserviceEC.ecomerce.order.kafka.orderConfirmation;
import com.MicroserviceEC.ecomerce.order.kafka.orderProducer;
import com.MicroserviceEC.ecomerce.order.payment.PaymentRequest;
import com.MicroserviceEC.ecomerce.order.payment.paymentClient;
import com.MicroserviceEC.ecomerce.order.products.ProductClient;
import com.MicroserviceEC.ecomerce.orderLine.Dtos.OrderLineReqeust;
import com.MicroserviceEC.ecomerce.orderLine.Services.OrderLineService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class orderService {

    private final orderRepository _orderRepo;
    private final CustomerClient _customerClient;
    private final ProductClient _productClient;
    private final OrderMapper mapper;
    private final OrderLineService _orderLineService;
    private final orderProducer _orderProducerkafka;
    private final paymentClient _paymentClient;
    public @Nullable Integer createOrder(orderRequest request) {


        // check customer  from customer domain  --> open feign


        var customer = _customerClient.findById(request.customerId())
                .orElseThrow(()->new BussinessException(" cant create order :: customer with id "+ request.customerId()+"not found "));

        System.out.println(customer);

        //purchase product from  product domain   // i can use here open feign also but i will choose RestTemplate


        var product = _productClient.purchaseProducts(request.products());

        System.out.println(product);


        //persist order

        var order = _orderRepo.save(mapper.toOrder(request));

        System.out.println(order);

        //persist order lines


        for (var req : request.products() ){


            _orderLineService.SaveOrderLine(new OrderLineReqeust(

              null,order.getId(),req.product_id(),req.quantity()
            ));

        }




        // start payment process  (choose method, start payment)


        var paymentRequest = new PaymentRequest(request.amount(),
                request.paymentMethod(),order.getId(),
                order.getReference(),
                customer
        );
        _paymentClient.requestOrderPayment(paymentRequest);

        // send async message to notification (kafka)

        _orderProducerkafka.sendConfirmation(
                new orderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        product
                )
        );

        return order.getId();

    }



    public  List<OrderResponse> findAllOrders() {

        return _orderRepo.findAll()
                .stream()
                .map(this.mapper::toOrderResponse)
                .collect(Collectors.toList());
    }
    public  OrderResponse findById(Integer orderId) {


        return _orderRepo.findById(orderId).map(mapper::toOrderResponse).orElseThrow(
                ()-> new EntityNotFoundException("order with id "+ orderId +" not found")
        );
    }


}
