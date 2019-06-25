package ru.merann.practicaltask.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.merann.practicaltask.soap.jaxb.*;
import ru.merann.practicaltask.soap.service.OrderSoapService;

@Endpoint
public class OrdersEndpoint {
    private static final String NAMESPACE_URI = "http://practicaltask.merann.ru/soap/jaxb";

    private final OrderSoapService orderService;

    @Autowired
    public OrdersEndpoint(OrderSoapService orderService) {
        this.orderService = orderService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOrdersRequest")
    @ResponsePayload
    public GetOrdersResponse getOrders(@RequestPayload GetOrdersRequest request) {
        return orderService.getOrders(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOrderRequest")
    @ResponsePayload
    public GetOrderResponse getOrder(@RequestPayload GetOrderRequest request) {
        return orderService.getOrder(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createOrderRequest")
    @ResponsePayload
    public CreateOrderResponse createOrder(@RequestPayload CreateOrderRequest request) {
        return orderService.createOrder(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateOrderRequest")
    @ResponsePayload
    public UpdateOrderResponse updateOrder(@RequestPayload UpdateOrderRequest request) {
        return orderService.updateOrder(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteOrderRequest")
    @ResponsePayload
    public DeleteOrderResponse deleteOrder(@RequestPayload DeleteOrderRequest request) {
        return orderService.deleteOrder(request);
    }
}
