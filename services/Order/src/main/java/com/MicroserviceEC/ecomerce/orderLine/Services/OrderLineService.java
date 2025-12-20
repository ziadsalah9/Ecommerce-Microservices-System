package com.MicroserviceEC.ecomerce.orderLine.Services;

import com.MicroserviceEC.ecomerce.orderLine.Dtos.OrderLineReqeust;
import com.MicroserviceEC.ecomerce.orderLine.Dtos.OrderLineResponse;
import com.MicroserviceEC.ecomerce.orderLine.Mapping.OrderLineMappper;
import com.MicroserviceEC.ecomerce.orderLine.Repository.OrderLineRepo;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {


    private final OrderLineRepo _orderRepo;
    private final OrderLineMappper mapper;

    public Integer SaveOrderLine(OrderLineReqeust orderLineReqeust) {

       var orderline =  mapper.toOrderLine(orderLineReqeust);

        return _orderRepo.save(orderline).getId();
    }

    public @Nullable List<OrderLineResponse> findAllOrderlinesByOrderId(Integer orderId) {

        var orderlines  = _orderRepo.findAllOrderLinesByOrderId(orderId).stream()
                .map(mapper::toOrderLineResonse).collect(Collectors.toList());
        return orderlines;
    }
}
