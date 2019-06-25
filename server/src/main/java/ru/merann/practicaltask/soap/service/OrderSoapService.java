package ru.merann.practicaltask.soap.service;

import ru.merann.practicaltask.soap.jaxb.*;

public interface OrderSoapService {
    GetOrdersResponse getOrders(GetOrdersRequest request);

    GetOrderResponse getOrder(GetOrderRequest request);

    CreateOrderResponse createOrder(CreateOrderRequest request);

    UpdateOrderResponse updateOrder(UpdateOrderRequest request);

    DeleteOrderResponse deleteOrder(DeleteOrderRequest request);
}
