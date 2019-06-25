package ru.merann.practicaltask.rest.service;

import ru.merann.practicaltask.rest.model.OrderDTO;
import ru.merann.practicaltask.rest.model.OrderFilter;

import java.util.List;

public interface OrderRestService {
    List<OrderDTO> getOrders(Long userId, String state);

    OrderDTO getOrder(Long orderId);

    OrderDTO createOrder(OrderDTO orderDTO);

    OrderDTO updateOrder(Long orderId, OrderDTO orderDTO);

    void deleteOrder(Long orderId);
}
